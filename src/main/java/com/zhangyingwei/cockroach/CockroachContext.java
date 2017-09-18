package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.TaskExecuter;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.client.HttpClientProxy;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContext {
    private Logger logger = Logger.getLogger(CockroachContext.class);
    private CockroachConfig config;
    private int thread = 5;
    private HttpProxy proxy = null;
    private ExecutorService service = Executors.newCachedThreadPool();

    public CockroachContext(CockroachConfig config) {
        this.config = config;
    }

    public CockroachContext thread(int thread) {
        this.thread = thread;
        return this;
    }

    /**
     * 启动爬虫程序
     * @param queue
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void start(TaskQueue queue) throws IllegalAccessException, InstantiationException {
        logger.info("starting...");
        config.print();
        this.thread = config.getThread() == 0 ? this.thread : config.getThread();
        for (int i = 0; i < thread; i++) {
            TaskExecuter executer = new TaskExecuter(queue, this.bulidHttpClient(), this.config.getStore().newInstance(), this.config.getThreadSleep(), this.config.isAutoClose());
            logger.info("new thread:" + executer.getId());
            service.execute(executer);
        }
        /**
         * 不可以再继续 提交新的任务 已经提交的任务不影响
         */
        service.shutdown();
        logger.info("start success");
    }

    public void stop() {
        service.shutdown();
    }

    private HttpClient bulidHttpClient() throws IllegalAccessException, InstantiationException {
        logger.info("bulid httpclient");
        if(this.config.getProxys() != null && this.proxy ==null){
            this.proxy = new HttpProxy(this.config.getProxys());
        }
        HttpClient client  = this.config.getHttpClient().newInstance();
        return new HttpClientProxy(client)
                .setTaskErrorHandler(this.config.getTaskErrorHandler().newInstance())
                .setProxy(this.proxy)
                .setCookie(this.config.getCookie())
                .setHttpHeader(this.config.getHttpHeader());
    }
}
