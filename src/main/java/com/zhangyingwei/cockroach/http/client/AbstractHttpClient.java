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
    protected ProxyTuple currentProxy;
    protected String cookie;
    protected Map<String, String> httpHeader = new HashMap<String,String>();

    @Override
    public IHttpClient setHttpHeader(Map<String, String> httpHeader) throws Exception {
        this.httpHeader = httpHeader;
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() throws Exception {
        return currentProxy;
    }
}
