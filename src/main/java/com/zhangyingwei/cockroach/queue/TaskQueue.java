package com.zhangyingwei.cockroach.queue;


import com.zhangyingwei.cockroach.executer.Task;
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
    private Map<Task,Integer> faildCounter;

    private static TaskQueue taskQueue;
    private IQueueTaskFilter filter = new DefaultQueueTaskFilter();

    public static TaskQueue of(){
        return TaskQueue.of(Integer.MAX_VALUE);
    }

    public static TaskQueue of(int calacity){
        return new TaskQueue(calacity);
    }

    private TaskQueue(int calacity) {
        this.queue = new LinkedBlockingDeque<Task>(calacity);
        this.faildQueue = new LinkedBlockingDeque<Task>();
        this.faildCounter = new HashMap<Task, Integer>();
        logger.info("create queue whith calacity " + calacity);
    }

    @Override
    public Task poll() throws InterruptedException {
        if(this.queue.isEmpty()){
            logger.info(Thread.currentThread().getName() + " queue is empty");
        }
        Task task = this.queue.poll();
        logger.info(Thread.currentThread().getName() + " pull task " + task);
        return task;
    }

    @Override
    public Task take() throws InterruptedException {
        if(this.queue.isEmpty()){
            logger.info(Thread.currentThread().getName() + " queue is empty");
        }
        Task task = this.queue.take();
        logger.info(Thread.currentThread().getName() + " take task " + task);
        return task;
    }

    @Override
    public void push(Task task) throws InterruptedException {
        if (this.filter.accept(task)) {
            this.queue.put(task);
            logger.info(Thread.currentThread().getName() + " push task " + task);
        } else {
            logger.info(Thread.currentThread().getName() + " " + task +" is not accept by " + this.filter.getClass());
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
