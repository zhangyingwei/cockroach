package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpParams;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.ProxyTuple;
import com.zhangyingwei.cockroach.http.client.okhttp.CookieManager;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import net.sf.json.JSONObject;
import okhttp3.*;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public abstract class AbsHttpClient implements HttpClient{
    protected HttpProxy proxy;
    protected ProxyTuple proxyTuple;
    protected String cookie;
    protected Map<String, String> httpHeader = new HashMap<String,String>();
    protected ITaskErrorHandler taskErrorHandler;

    @Override
    public HttpClient setProxy(HttpProxy proxy) {
        this.proxy = proxy;
        return this;
    }

    @Override
    public abstract TaskResponse doGet(Task task) throws Exception;

    @Override
    public abstract HttpClient proxy();

    @Override
    public abstract TaskResponse doPost(Task task) throws Exception;

    @Override
    public abstract HttpClient setCookie(String cookie);

    @Override
    public HttpClient setHttpHeader(Map<String, String> httpHeader) {
        this.httpHeader = httpHeader;
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() {
        return proxyTuple;
    }
}
