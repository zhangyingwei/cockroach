package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.store.PrintStore;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by zhangyw on 2017/8/10.
 * cockroach 爬虫 配置类，主要配置有
 * 应用名 http客户端 结果处理类 代理 线程数 任务处理完毕线程操作（等待/结束） cookie httpheader 任务失败处理逻辑
 */
public class CockroachConfig {
    private Logger logger = Logger.getLogger(CockroachConfig.class);
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

    static {
        if(true){
            Logger logger = Logger.getRootLogger();
            System.err.println("log4j.properties is not found , use default log4j config");
            logger.setLevel(Level.DEBUG);
            logger.addAppender(new ConsoleAppender(new PatternLayout("[%-5p][%-20d{yyyy/MM/dd HH:mm:ss}] %m%n")));
        }
    }

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
        System.setProperty(Constants.APP_NAME_KEY, appName);
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
        logger.info("AppName: "+this.getAppName());
        logger.info("Proxys: "+this.getProxys());
        logger.info("Threads: "+this.getThread());
        logger.info("ThreadSleep: "+this.getThreadSleep());
        logger.info("HttpClient: "+this.getHttpClient());
        logger.info("Store: "+this.getStore());
        logger.info("Cookie: "+this.getCookie());
        logger.info("HttpHeaders: "+this.getHttpHeader());
        logger.info("AutoClose: "+this.autoClose);
        logger.info("TaskErrorHandler: "+this.getTaskErrorHandler());
    }
}
