package com.zhangyingwei.cockroach.executer.response;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.http.client.IHttpClient;
import com.zhangyingwei.cockroach.queue.CockroachQueue;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangyw on 2017/9/19.
 * 失败任务封装
 */
public class TaskErrorResponse implements ICockroachResponse {
    private Task task;
    private String message;
    private CockroachQueue queue;
    private String charset;
    private TaskResponse response;
    private IHttpClient httpClient;

    public TaskErrorResponse(TaskResponse response) throws IOException {
        this.response = response;
    }

    @Override
    public ResponseContent getContent() throws IOException {
        return this.response.getContent();
    }

    @Override
    public Task getTask() {
        return this.response.getTask();
    }

    @Override
    public boolean isGroup(String group) {
        return this.response.isGroup(group);
    }

    @Override
    public boolean isGroupStartWith(String groupPrefix) {
        return this.response.isGroupStartWith(groupPrefix);
    }

    @Override
    public boolean isGroupEndWith(String groupEnd) {
        return this.response.isGroupEndWith(groupEnd);
    }

    @Override
    public boolean isGroupContains(String str) {
        return this.response.isGroupContains(str);
    }

    @Override
    public CockroachQueue getQueue() {
        return this.response.getQueue();
    }

    @Override
    public List<String> header(String key) {
        return this.response.header(key);
    }

    public TaskResponse response(){
        return this.response;
    }
}