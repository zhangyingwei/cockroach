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
            taskQueue = new TaskQueue(calacity);
        }
        return taskQueue;
    }

    private TaskQueue(int calacity) {
        this.queue = new ArrayBlockingQueue<Task>(calacity);
    }

    public Task poll() throws InterruptedException {
        return this.queue.poll();
    }

    public Task take() throws InterruptedException {
        return this.queue.take();
    }

    public void push(Task task) throws InterruptedException {
        this.queue.put(task);
    }

    public void push(List<String> urls) {
        urls.stream().map(url -> new Task(url)).forEach(task -> {
            try {
                this.queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void clear(){
        this.queue.clear();
    }
}
