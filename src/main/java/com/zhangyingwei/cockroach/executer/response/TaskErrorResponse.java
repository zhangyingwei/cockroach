package com.zhangyingwei.cockroach.executer.response;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;

import java.io.IOException;

/**
 * Created by zhangyw on 2017/9/19.
 * 失败任务封装
 */
public class TaskErrorResponse implements ICockroachResponse {
    private Task task;
    private String message;
    private CockroachQueue queue;
    private String charset;

    public TaskErrorResponse(TaskResponse response) {
        this.task =response.getTask();
        this.queue = response.getQueue();
        this.message = response.getMessage();
    }

    @Override
    public String getContent() throws IOException {
        return message;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public boolean isGroup(String group) {
        return false;
    }

    @Override
    public boolean isGroupStartWith(String groupPrefix) {
        return task.getGroup().startsWith(groupPrefix);
    }

    @Override
    public boolean isGroupEndWith(String end) {
        return task.getGroup().endsWith(end);
    }

    @Override
    public boolean isGroupContains(String str) {
        return task.getGroup().contains(str);
    }

    @Override
    public CockroachQueue getQueue() {
        return queue;
    }
}