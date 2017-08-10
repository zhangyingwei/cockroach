package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.executer.task.ITask;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuter implements Runnable {

    private TaskQueue queue;

    public TaskExecuter(TaskQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ITask task = this.queue.pull();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
