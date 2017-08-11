package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.store.IStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachConfig {
    public static final String APPNAME_KEY = "cockroach.app.name";
    private String appName;
    private String proxys = null;
    private int thread = 0;
    private Class<? extends HttpClient> httpClient;
    private Class<? extends IStore> store;
    private String cookie;
    private Map<String, String> httpHeader;

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
        System.setProperty(APPNAME_KEY, appName);
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

    public Class<? extends HttpClient> getHttpClient() {
        return httpClient;
    }

    public CockroachConfig setHttpClient(Class<? extends HttpClient> httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public Class<? extends IStore> getStore() {
        return store;
    }

    public CockroachConfig setStore(Class<? extends IStore> store) {
        this.store = store;
        return this;
    }

    public CockroachConfig setCookie(String cookid) {
        this.cookie = cookid;
        return this;
    }

    public String getCookie() {
        return cookie;
    }

    public CockroachConfig addHttpHeader(String key, String value) {
        if(this.httpHeader == null){
            this.httpHeader = new HashMap<String, String>();
        }
        this.httpHeader.put(key, value);
        return this;
    }

    public Map<String, String> getHttpHeader() {
        return httpHeader;
    }
}
