package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.listener.DefaultExecutersListener;
import com.zhangyingwei.cockroach.executer.listener.IExecutersListener;
import com.zhangyingwei.cockroach.executer.response.filter.ITaskResponseFilter;
import com.zhangyingwei.cockroach.executer.response.filter.TaskResponseFilterBox;
import com.zhangyingwei.cockroach.executer.task.TaskExecuter;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.client.HttpClientProxy;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangyw
 * @date: 2018/1/29
 * @time: 下午2:14
 * @desc:
 */
public class ExecuterManager {
    private int thread;
    private HttpProxy proxy = null;
    private ExecutorService service = Executors.newCachedThreadPool();
    private CockroachConfig config;
    private Logger logger = Logger.getLogger(ExecuterManager.class);
    private IExecutersListener executerListener = new DefaultExecutersListener();

    public ExecuterManager(CockroachConfig config) {
        this.config = config;
    }

    public void start(CockroachQueue queue) throws Exception {
        this.thread = config.getThread();
        TaskResponseFilterBox filterBox = this.bulidResponseFilters();
        this.executerListener.onStart();
        for (int i = 0; i < thread; i++) {
            TaskExecuter executer = new TaskExecuter(queue, this.bulidHttpClient(), this.config.getStore().newInstance(), this.config.getTaskErrorHandler().newInstance(), this.config.getThreadSleep(), this.config.isAutoClose(),filterBox);
            logger.info("new thread:" + executer.getId());
            service.execute(executer);
        }
        /**
         * 不可以再继续 提交新的任务 已经提交的任务不影响
         */
        service.shutdown();

        /**
         * 每 5 秒检测是否全部线程执行完毕
         */
        while (true) {
            TimeUnit.SECONDS.sleep(5);
            if (service.isTerminated()) {
                logger.info("任务已经全部执行完毕");
                break;
            }
        }
        this.executerListener.onEnd();
    }

    /**
     * bind executer listener
     * @param listener
     * @return
     */
    public ExecuterManager bindListener(Class<? extends IExecutersListener> listener) throws IllegalAccessException, InstantiationException {
        if (listener != null) {
            this.executerListener = listener.newInstance();
        }
        return this;
    }

    private TaskResponseFilterBox bulidResponseFilters() throws IllegalAccessException, InstantiationException {
        logger.info("bulid response filters");
        TaskResponseFilterBox filterBox = new TaskResponseFilterBox();
        Set<Class<? extends ITaskResponseFilter>> filters = this.config.getResponseFilters();
        for (Class<? extends ITaskResponseFilter> filter : filters) {
            filterBox.addFilter(filter.newInstance());
        }
        return filterBox;
    }

    private HttpClient bulidHttpClient() throws Exception {
        logger.info("bulid httpclient");
        if(this.config.getProxys() != null && this.proxy ==null){
            this.proxy = new HttpProxy(this.config.getProxys());
        }
        HttpClient client  = this.config.getHttpClient().newInstance();

        StringGenerator cookieGenerator = null;
        if (this.config.getCookieGenerator() != null) {
            cookieGenerator = (StringGenerator) this.config.getCookieGenerator().newInstance();
        }

        MapGenerator headerGenerator = null;
        if (this.config.getHeaderGenerator() != null) {
            headerGenerator = (MapGenerator) this.config.getHeaderGenerator().newInstance();
        }

        return new HttpClientProxy(client)
                .setCookieGenerator(cookieGenerator)
                .setHeaderGenerator(headerGenerator)
                .setProxy(this.proxy)
                .setCookie(this.config.getCookie())
                .setHttpHeader(this.config.getHttpHeader())
                .showProgress(this.config.getShowHttpClientProgress());
    }
}
