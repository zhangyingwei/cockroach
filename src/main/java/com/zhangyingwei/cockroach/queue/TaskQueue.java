package com.zhangyingwei.cockroach.queue;


import com.zhangyingwei.cockroach.config.Constants;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.utils.CockroachUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zhangyw on 2017/8/10.
 * 消息队列
 */
public class TaskQueue implements CockroachQueue {
    private Logger logger = Logger.getLogger(TaskQueue.class);

    private BlockingQueue<Task> queue;
    private BlockingQueue<Task> faildQueue;

    private static TaskQueue taskQueue;
    private IQueueTaskFilter filter = new DefaultQueueTaskFilter();

    public static TaskQueue of(){
        return TaskQueue.of(Integer.MAX_VALUE);
    }

    public static TaskQueue of(int calacity){
        return new TaskQueue(calacity);
    }

    public TaskQueue(Integer calacity) {
        this.queue = new LinkedBlockingDeque<Task>(calacity);
        this.faildQueue = new LinkedBlockingDeque<Task>();
        logger.info("create queue whith calacity " + calacity);
    }

    @Override
    public Task poll() throws InterruptedException {
        this.queueValid();
        Task task = this.queue.poll();
        logger.info(Thread.currentThread().getName() + " pull task " + task);
        return task;
    }

    @Override
    public Task take() throws InterruptedException {
        this.queueValid();
        Task task = this.queue.take();
        logger.info(Thread.currentThread().getName() + " take task " + task);
        return task;
    }

    /**
     * 如果 queue 为空
     * 如果 task retry 次数小于系统设置的 DEFAULT_TASK_RESTY
     * 把失败任务重新添加到队列中
     */
    private synchronized void queueValid() throws InterruptedException {
        if(this.queue.isEmpty() && !this.faildQueue.isEmpty()){
            for (Task task : this.faildQueue) {
                this.push(task);
            }
            this.faildQueue.clear();
        }
        if (this.queue.isEmpty()) {
            logger.info(Thread.currentThread().getName() + " queue is empty");
        }
    }

    @Override
    public void push(Task task) throws InterruptedException {
        if (this.filter.accept(task)) {
            this.queue.put(task);
            logger.info(Thread.currentThread().getName() + " push task " + task);
        } else {
            logger.info(Thread.currentThread().getName() + " " + task +" is not accepted by " + this.filter.getClass());
        }
    }

    @Override
    public synchronized void falied(Task task) throws InterruptedException {
        if (this.filter.accept(task)) {
            if (task.getRetry() > 0) {
                this.faildQueue.put(task);
                logger.info(Thread.currentThread().getName() + " push failed task " + task);
            }
        } else {
            logger.info(Thread.currentThread().getName() + " " + task +" is not accepted by " + this.filter.getClass());
        }
    }

    @Override
    public void pushAll(List<Task> tasks) throws InterruptedException {
        for (Task task : tasks) {
            this.push(task);
        }
    }

    @Override
    public void push(List<String> urls) {
        urls.stream().map(url -> new Task(url)).forEach(task -> {
            try {
                this.push(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void clear(){
        this.queue.clear();
        logger.info(Thread.currentThread().getName() + " clear queue");
    }

    @Override
    public CockroachQueue filter(IQueueTaskFilter filter) throws Exception {
        this.filter = filter;
        return this;
    }
}
