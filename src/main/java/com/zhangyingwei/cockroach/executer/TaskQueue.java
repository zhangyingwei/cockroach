package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.executer.task.ITask;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskQueue {
    private ArrayBlockingQueue<ITask> queue;

    public TaskQueue() {
        this.queue = new ArrayBlockingQueue(1024);
    }

    public ITask pull() throws InterruptedException {
        return this.queue.take();
    }

    public void push(ITask task) throws InterruptedException {
        this.queue.put(task);
    }

    public void clear(){
        this.queue.clear();
    }

}
