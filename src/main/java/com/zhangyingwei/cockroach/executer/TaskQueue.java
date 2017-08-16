package com.zhangyingwei.cockroach.executer;


import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskQueue {
    private ArrayBlockingQueue<Task> queue;

    private static TaskQueue taskQueue;

    public static TaskQueue of(){
        return TaskQueue.of(1024);
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
        this.queue = new ArrayBlockingQueue<Task>(calacity);
    }

    public Task poll() throws InterruptedException {
        synchronized (TaskQueue.class){
            return this.queue.poll();
        }
    }

    public Task take() throws InterruptedException {
        synchronized (TaskQueue.class){
            return this.queue.take();
        }
    }

    public void push(Task task) throws InterruptedException {
        synchronized (TaskQueue.class){
            this.queue.put(task);
        }
    }

    public void pushAll(List<Task> tasks) throws InterruptedException {
        synchronized (TaskQueue.class) {
            for (Task task : tasks) {
                this.queue.put(task);
            }
        }
    }

    public void push(List<String> urls) {
        synchronized (TaskQueue.class){
            urls.stream().map(url -> new Task(url)).forEach(task -> {
                try {
                    this.queue.put(task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void clear(){
        synchronized (TaskQueue.class){
            this.queue.clear();
        }
    }
}
