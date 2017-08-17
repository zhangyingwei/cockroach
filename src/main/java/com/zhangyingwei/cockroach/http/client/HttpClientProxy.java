package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.ProxyTuple;
import com.zhangyingwei.cockroach.http.exception.Http30XException;
import com.zhangyingwei.cockroach.http.exception.Http40XException;
import com.zhangyingwei.cockroach.http.exception.Http50XException;
import com.zhangyingwei.cockroach.http.exception.HttpException;
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
    private HttpProxy proxy;

    public HttpClientProxy(HttpClient client) {
        this.client = client;
    }

    @Override
    public HttpClient setProxy(HttpProxy proxy) {
        this.proxy = proxy;
        this.client.setProxy(proxy);
        return this;
    }

    @Override
    public TaskResponse doGet(Task task) {
        try {
            return this.client.doGet(task);
        } catch (Exception e) {
            String message = "";
            if (e instanceof HttpException) {
                if(e instanceof Http40XException){
                    message = "resources not found";
                }else if(e instanceof Http50XException){
                    message = "server error";
                }else if(e instanceof Http30XException){
                    message = "resources redirect:" + e.getMessage();
                }
            } else {
                if (this.proxy != null) {
                    this.proxy.disable(this.getCurrentProxyTuple());
                }
                message = e.getMessage();
            }
            System.out.println("ERROR: " + task + " - " + message);
//            this.taskErrorhandler.error(task, e.getMessage());
        }
        return TaskResponse.empty().setTask(task);
    }

    @Override
    public HttpClient proxy() {
        this.client.proxy();
        return this;
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
        this.client.setCookie(cookie);
        return this;
    }

    @Override
    public HttpClient setHttpHeader(Map<String, String> httpHeader) {
        this.client.setHttpHeader(httpHeader);
        return this;
    }

    @Override
    public HttpClient setTaskErrorHandler(ITaskErrorHandler taskErrorHandler) {
        this.taskErrorhandler = taskErrorHandler;
        this.client.setTaskErrorHandler(taskErrorHandler);
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() {
        return this.client.getCurrentProxyTuple();
    }
}
