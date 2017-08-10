package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.TaskExecuter;
import com.zhangyingwei.cockroach.executer.TaskQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContext {

    private CockroachConfig config;
    private int thread = 5;
    private TaskQueue queue;

    public CockroachContext(CockroachConfig config) {
        this.config = config;
    }

    public CockroachContext thread(int thread) {
        this.thread = thread;
        return this;
    }

    public void setQueue(TaskQueue queue) {
        this.queue = queue;
    }

    public void start(){
        ExecutorService service = Executors.newCachedThreadPool();
        this.thread = config.getThread() == 0 ? this.thread : config.getThread();
        for (int i = 0; i < thread; i++) {
            service.execute(new TaskExecuter(queue,this.config.getHttpClient(),this.config.getStore()));
        }
    }
}
