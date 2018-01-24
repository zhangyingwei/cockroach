package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.response.filter.ITaskResponseFilter;
import com.zhangyingwei.cockroach.executer.response.filter.TaskResponseFilterBox;
import com.zhangyingwei.cockroach.executer.task.TaskExecuter;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.client.HttpClientProxy;
import org.apache.log4j.Logger;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContext {
    private Logger logger = Logger.getLogger(CockroachContext.class);
    private CockroachConfig config;
    private int thread;
    private HttpProxy proxy = null;
    private ExecutorService service = Executors.newCachedThreadPool();
    private boolean started = false;

    public CockroachContext(final CockroachConfig config) {
        this.config = config;
    }

    /**
     * 启动爬虫程序
     * 只能启动一次，启动之前先判断之前有没有启动过
     * @param queue
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void start(CockroachQueue queue) throws Exception {
        if(!started){
            logger.info("starting...");
            config.print();
            this.thread = config.getThread();
            TaskResponseFilterBox filterBox = this.bulidResponseFilters();
            for (int i = 0; i < thread; i++) {
                TaskExecuter executer = new TaskExecuter(queue, this.bulidHttpClient(), this.config.getStore().newInstance(), this.config.getTaskErrorHandler().newInstance(), this.config.getThreadSleep(), this.config.isAutoClose(),filterBox);
                logger.info("new thread:" + executer.getId());
                service.execute(executer);
            }
            /**
             * 不可以再继续 提交新的任务 已经提交的任务不影响
             */
            service.shutdown();
            this.started = true;
            logger.info("start success");
        }else{
            logger.warn("the cockroach has already started");
        }
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
