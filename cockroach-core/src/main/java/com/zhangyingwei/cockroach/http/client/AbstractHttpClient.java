package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.ProxyTuple;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/10.
 */
public abstract class AbstractHttpClient implements IHttpClient {
    protected HttpProxy proxy;
    protected ProxyTuple proxyTuple;
    protected String cookie;
    protected Map<String, String> httpHeader = new HashMap<String,String>();
    protected ITaskErrorHandler taskErrorHandler;

    @Override
    public IHttpClient setProxy(HttpProxy proxy) throws Exception {
        this.proxy = proxy;
        return this;
    }

    @Override
    public abstract TaskResponse doGet(Task task) throws Exception;

    @Override
    public abstract IHttpClient proxy() throws Exception;

    @Override
    public abstract TaskResponse doPost(Task task) throws Exception;

    @Override
    public abstract IHttpClient setCookie(String cookie) throws Exception;

    @Override
    public IHttpClient setHttpHeader(Map<String, String> httpHeader) throws Exception {
        this.httpHeader = httpHeader;
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() throws Exception {
        return proxyTuple;
    }

    protected boolean randomProxy(){
        if(this.proxy != null && !this.proxy.isEmpty()) {
            this.proxyTuple = this.proxy.randomProxy();
        }
        return this.proxyTuple != null;
    }
}
