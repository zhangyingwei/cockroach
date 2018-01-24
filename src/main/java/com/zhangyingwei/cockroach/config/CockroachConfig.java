package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.response.filter.ITaskResponseFilter;
import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.CockroachUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.util.*;

/**
 * Created by zhangyw on 2017/8/10.
 * cockroach 爬虫 配置类，主要配置有
 * 应用名 http客户端 结果处理类 代理 线程数 任务处理完毕线程操作（等待/结束） cookie httpheader 任务失败处理逻辑
 */
public class CockroachConfig {
    private Logger logger = Logger.getLogger(CockroachConfig.class);
    private String appName;
    private String proxys = null;
    private int thread = 1;
    private int threadSleep = 0;
    private Class<? extends HttpClient> httpClient = Constants.HTTP_CLIENT;
    private Boolean showHttpClientProgress = Constants.HTTP_SHOWHTTPCLIENTPROGRESS;
    private Class<? extends IStore> store = Constants.STORE;
    private String cookie;
    private Class<? extends StringGenerator> cookieGenerator = Constants.COOKIDGENERATOR;
    private Class<? extends MapGenerator> headerGenerator = Constants.HEADERGENERATOR;
    private Map<String, String> httpHeader;
    private boolean autoClose = false;
    private Class<? extends ITaskErrorHandler> taskErrorHandler;
    private Set<Class<? extends ITaskResponseFilter>> responseFilters = new HashSet<Class<? extends ITaskResponseFilter>>();

    /**
     * 如果找不到 log4j 的配置，就使用默认配置
     */
    static {
        Logger elogger = Logger.getLogger(CockroachConfig.class);
        if(!elogger.getParent().getAllAppenders().hasMoreElements()){
            Logger logger = Logger.getRootLogger();
            System.err.println("log4j.properties is not found , use default log4j config");
            logger.setLevel(Level.DEBUG);
            logger.addAppender(new ConsoleAppender(new PatternLayout("[%-5p][%-20d{yyyy/MM/dd HH:mm:ss}][%c] %m%n")));
        }
    }

    public String getProxys() {
        return proxys;
    }

    public CockroachConfig setProxys(String proxys) {
        CockroachUtils.addSystemPropertie(Constants.APP_PROXY_KEY,proxys);
        this.proxys = proxys;
        return this;
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public CockroachConfig setAutoClose(boolean autoClose) {
        CockroachUtils.addSystemPropertie(Constants.APP_AUTOCLOSE_KEY,autoClose);
        this.autoClose = autoClose;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public CockroachConfig setAppName(String appName) {
        CockroachUtils.addSystemPropertie(Constants.APP_NAME_KEY,appName);
        this.appName = appName;
        return this;
    }

    public int getThread() {
        return thread;
    }

    public CockroachConfig setThread(int thread) {
        CockroachUtils.addSystemPropertie(Constants.APP_THREAD_KEY,thread);
        this.thread = thread;
        return this;
    }

    public CockroachConfig setThread(int thread, int sleep) {
        CockroachUtils.addSystemPropertie(Constants.APP_THREAD_KEY,thread);
        CockroachUtils.addSystemPropertie(Constants.APP_THREAD_SLEEP_KEY,sleep);
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

    @SuppressWarnings("not supported")
    public CockroachConfig setHttpClient(Class<? extends HttpClient> httpClient) {
        CockroachUtils.addSystemPropertie(Constants.APP_HTTPCLIENT_KEY,httpClient);
        this.httpClient = httpClient;
        return this;
    }

    public Class<? extends IStore> getStore() {
        return store;
    }

    public CockroachConfig setStore(Class<? extends IStore> store) {
        CockroachUtils.addSystemPropertie(Constants.APP_STORE_KEY,store);
        this.store = store;
        return this;
    }

    public CockroachConfig setCookie(String cookie) {
        CockroachUtils.addSystemPropertie(Constants.APP_COOKIE_KEY,cookie);
        this.cookie = cookie;
        this.addHttpHeader("Cookie", cookie);
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
        CockroachUtils.addSystemPropertie(Constants.APP_TASK_ERROR_KEY,taskErrorHandler);
        this.taskErrorHandler = taskErrorHandler;
        return this;
    }

    public CockroachConfig setShowHttpClientProgress(Boolean showHttpClientProgress) {
        this.showHttpClientProgress = showHttpClientProgress;
        return this;
    }

    public Boolean getShowHttpClientProgress() {
        return showHttpClientProgress;
    }

    public Class<?> getCookieGenerator() {
        return cookieGenerator;
    }

    public CockroachConfig setCookieGenerator(Class<?> cookieGenerator) {
        this.cookieGenerator = (Class<? extends StringGenerator>) cookieGenerator;
        return this;
    }

    public Class<? extends MapGenerator> getHeaderGenerator() {
        return headerGenerator;
    }

    public CockroachConfig setHeaderGenerator(Class<? extends MapGenerator> headerGenerator) {
        this.headerGenerator = headerGenerator;
        return this;
    }

    public Set<Class<? extends ITaskResponseFilter>> getResponseFilters() {
        return responseFilters;
    }

    public CockroachConfig setResponseFilters(Set<Class<? extends ITaskResponseFilter>> responseFilters) {
        this.responseFilters = responseFilters;
        return this;
    }

    public CockroachConfig addResponseFilters(Class<? extends ITaskResponseFilter> responseFilter) {
        this.responseFilters.add(responseFilter);
        return this;
    }

    public void print() {
        logger.info("---------------------------config--------------------------");
        logger.info("AppName: "+this.getAppName());
        logger.info("Proxys: "+this.getProxys());
        logger.info("Threads: "+this.getThread());
        logger.info("ThreadSleep: "+this.getThreadSleep());
        logger.info("HttpClient: "+this.getHttpClient());
        logger.info("HttpClientProgress: "+this.getShowHttpClientProgress());
        logger.info("Store: "+this.getStore());
        logger.info("Cookie: "+this.getCookie());
        logger.info("CookieGenerator: "+this.getCookieGenerator());
        logger.info("HttpHeaders: "+this.getHttpHeader());
        logger.info("HttpHeadersGenerator: "+this.getHeaderGenerator());
        logger.info("AutoClose: "+this.autoClose);
        logger.info("TaskErrorHandler: "+this.getTaskErrorHandler());
        logger.info("ResponseFilters: "+this.getResponseFilters());
        logger.info("-------------------------------------------------------------");
    }
}
