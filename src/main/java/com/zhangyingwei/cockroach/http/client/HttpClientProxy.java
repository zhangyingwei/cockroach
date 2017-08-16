package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;

import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/8/16
 * @time: 下午8:52
 * @desc:
 */
public class HttpClientProxy implements HttpClient {

    private HttpClient client;
    private ITaskErrorHandler taskErrorhandler;

    public HttpClientProxy(HttpClient client) {
        this.client = client;
    }

    @Override
    public HttpClient setProxy(HttpProxy proxy) {
        return this.client.setProxy(proxy);
    }

    @Override
    public TaskResponse doGet(Task task) {
        try {
            return this.client.doGet(task);
        } catch (Exception e) {
            this.taskErrorhandler.error(task, e.getMessage());
            System.out.println("ERROR: "+task + " - " +e.getMessage());
        }
        return TaskResponse.empty().setTask(task);
    }

    @Override
    public HttpClient proxy() {
        return this.client.proxy();
    }

    @Override
    public TaskResponse doPost(Task task) {
        try {
            return this.client.doPost(task);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return TaskResponse.empty().setTask(task);
        }
    }

    @Override
    public HttpClient setCookie(String cookie) {
        return this.client.setCookie(cookie);
    }

    @Override
    public HttpClient setHttpHeader(Map<String, String> httpHeader) {
        return this.client.setHttpHeader(httpHeader);
    }

    @Override
    public HttpClient setTaskErrorHandler(ITaskErrorHandler taskErrorHandler) {
        this.taskErrorhandler = taskErrorHandler;
        return this.client.setTaskErrorHandler(taskErrorHandler);
    }
}
