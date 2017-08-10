package com.zhangyingwei.cockroach.executer;


import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskQueue {
    private ArrayBlockingQueue<Task> queue;

    public TaskQueue() {
        this.queue = new ArrayBlockingQueue(1024);
    }

    public Task pull() throws InterruptedException {
        return this.queue.take();
    }

    public void push(Task task) throws InterruptedException {
        this.queue.put(task);
    }

    public void clear(){
        this.queue.clear();
    }

}
