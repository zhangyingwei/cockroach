package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.executer.response.ICockroachResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.ProxyTuple;

import java.util.Map;

/**
 * Created by zhangyw on 2017/8/10.
 */
public interface IHttpClient {
    TaskResponse doGet(Task task) throws Exception;

    IHttpClient proxy(ProxyTuple proxy) throws Exception;

    TaskResponse doPost(Task task) throws Exception;

    IHttpClient setCookie(String cookie) throws Exception;

    IHttpClient setHttpHeader(Map<String, String> httpHeader) throws Exception;

    ProxyTuple getCurrentProxyTuple() throws Exception;
}
