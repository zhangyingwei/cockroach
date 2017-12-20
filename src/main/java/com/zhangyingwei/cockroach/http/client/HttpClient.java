package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.ProxyTuple;

import java.util.Map;

/**
 * Created by zhangyw on 2017/8/10.
 */
public interface HttpClient {
    HttpClient setProxy(HttpProxy proxy) throws Exception;

    TaskResponse doGet(Task task) throws Exception;

    HttpClient proxy() throws Exception;

    TaskResponse doPost(Task task) throws Exception;

    HttpClient setCookie(String cookie) throws Exception;

    HttpClient setHttpHeader(Map<String, String> httpHeader) throws Exception;

    ProxyTuple getCurrentProxyTuple() throws Exception;

    HttpClient showProgress(Boolean show);
}
