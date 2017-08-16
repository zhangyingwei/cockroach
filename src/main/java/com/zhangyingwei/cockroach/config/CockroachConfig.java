package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.store.PrintStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachConfig {
    public static final String APPNAME_KEY = "cockroach.app.name";
    private static final Class HTTPCLIENT_DEFAULT = COkHttpClient.class;
    private static final Class STORE_DEFAULT = PrintStore.class;
    private String appName;
    private String proxys = null;
    private int thread = 1;
    private int threadSleep = 0;
    private Class<? extends HttpClient> httpClient = HTTPCLIENT_DEFAULT;
    private Class<? extends IStore> store = STORE_DEFAULT;
    private String cookie;
    private Map<String, String> httpHeader;
    private boolean autoClose = false;
    private Class<? extends ITaskErrorHandler> taskErrorHandler;

    public String getProxys() {
        return proxys;
    }

    public CockroachConfig setProxys(String proxys) {
        this.proxys = proxys;
        return this;
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public CockroachConfig setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
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

    public CockroachConfig setThread(int thread, int sleep) {
        this.thread = thread;
        this.threadSleep = sleep;
        return this;
    }

    public int getThreadSleep() {
        return threadSleep;
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

    public Class<? extends ITaskErrorHandler> getTaskErrorHandler() {
        return Optional.<Class>ofNullable(taskErrorHandler).orElse(DefaultTaskErrorHandler.class);
    }

    public CockroachConfig setTaskErrorHandler(Class<? extends ITaskErrorHandler> taskErrorHandler) {
        this.taskErrorHandler = taskErrorHandler;
        return this;
    }

    public void print() {
        System.out.println("INFO: AppName: "+this.getAppName());
        System.out.println("INFO: Proxys: "+this.getProxys());
        System.out.println("INFO: Threads: "+this.getThread());
        System.out.println("INFO: ThreadSleep: "+this.getThreadSleep());
        System.out.println("INFO: HttpClient: "+this.getHttpClient());
        System.out.println("INFO: Store: "+this.getStore());
        System.out.println("INFO: Cookie: "+this.getCookie());
        System.out.println("INFO: HttpHeaders: "+this.getHttpHeader());
        System.out.println("INFO: AutoClose: "+this.autoClose);
        System.out.println("INFO: TaskErrorHandler: "+this.getTaskErrorHandler());
    }
}
