package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.queue.CockroachQueue;

/**
 * Created by zhangyw on 2017/9/19.
 * 失败任务封装
 */
public class TaskErrorResponse {
    private Task task;
    private String message;
    private CockroachQueue queue;

    public TaskErrorResponse(TaskResponse response) {
        this.task =response.getTask();
        this.queue = response.getQueue();
        this.message = response.getMessage();
    }

    public Task getTask() {
        return task;
    }

    public String getMessage() {
        return message;
    }

    public CockroachQueue getQueue() {
        return queue;
    }
}
