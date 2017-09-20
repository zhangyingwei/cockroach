package com.zhangyingwei.cockroach.queue;


import com.zhangyingwei.cockroach.executer.Task;
import org.apache.log4j.Logger;
import java.util.List;
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
    private static TaskQueue taskQueue;

    public static TaskQueue of(){
        return TaskQueue.of(Integer.MAX_VALUE);
    }

    public static TaskQueue of(int calacity){
        if(taskQueue == null){
            synchronized (TaskQueue.class){
                if(taskQueue == null){
                    taskQueue = new TaskQueue(calacity);
                }
            }
        }
        return taskQueue;
    }

    private TaskQueue(int calacity) {
//        this.queue = new ArrayBlockingQueue<Task>(calacity);
        this.queue = new LinkedBlockingDeque<Task>();
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
        this.queue.put(task);
        logger.info(Thread.currentThread().getName() + " push task " + task);
    }

    @Override
    public void pushAll(List<Task> tasks) throws InterruptedException {
        for (Task task : tasks) {
            this.queue.put(task);
        }
        logger.info(Thread.currentThread().getName() + " put task list " + tasks.size());
    }

    @Override
    public void push(List<String> urls) {
        urls.stream().map(url -> new Task(url)).forEach(task -> {
            try {
                this.queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        logger.info(Thread.currentThread().getName() + " put urls list " + urls.size());
    }

    @Override
    public void clear(){
        this.queue.clear();
        logger.info(Thread.currentThread().getName() + " clear queue");
    }
}
