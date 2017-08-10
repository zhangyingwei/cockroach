package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.http.HttpClient;
import com.zhangyingwei.cockroach.store.IStore;

import java.util.Set;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachConfig {
    private String appName;
    private String proxys;
    private int thread = 0;
    private HttpClient httpClient;
    private IStore store;

    public String getProxys() {
        return proxys;
    }

    public CockroachConfig setProxys(String proxys) {
        this.proxys = proxys;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public CockroachConfig setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public int getThread() {
        return thread;
    }

    public CockroachConfig setThread(int thread) {
        this.thread = thread;
        return this;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public CockroachConfig setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public IStore getStore() {
        return store;
    }

    public CockroachConfig setStore(IStore store) {
        this.store = store;
        return this;
    }
}
