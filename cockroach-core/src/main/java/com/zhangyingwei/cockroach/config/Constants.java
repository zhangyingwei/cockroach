package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.NoCookieGenerator;
import com.zhangyingwei.cockroach.common.generators.NoHeaderGenerator;
import com.zhangyingwei.cockroach.executer.listener.DefaultExecutersListener;
import com.zhangyingwei.cockroach.executer.listener.IExecutersListener;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.cockroach.store.PrintStore;

/**
 * Created by zhangyw on 2017/9/13.
 * 常量统一管理
 */
public class Constants {
    public static final String APP_NAME_KEY = "cockroach.app.name";
    public static final String APP_PROXY_KEY = "cockroach.app.proxy";
    public static final String APP_AUTOCLOSE_KEY = "cockroach.app.autoclose";
    public static final String APP_THREAD_KEY = "cockroach.app.thread";
    public static final String APP_TASK_GROUP_DEFAULT = "default";
    public static final String APP_THREAD_SLEEP_KEY = "cockroach.app.thread.sleep";
    public static final String APP_HTTPCLIENT_KEY = "cockroach.app.httpclient";
    public static final String APP_STORE_KEY = "cockroach.app.store";
    public static final String APP_COOKIE_KEY = "cockroach.app.cookie";
    public static final String APP_TASK_ERROR_KEY = "cockroach.app.task.error";

    public static final String DEFAULT_APP_NAME = "cockroach";
    public static final Class<? extends ITaskErrorHandler> DEFAULT_TASKERROR_HANDLER = DefaultTaskErrorHandler.class;
    public static final int DEFAULT_THREAD_NUM = 10;
    public static final int DEFAULT_THREAD_SLEEP = 500; //毫秒
    public static final boolean DEFAULT_AUTO_CLOSE = false;

    public static final Boolean HTTP_SHOWHTTPCLIENTPROGRESS = false;

    public static final Class HTTP_CLIENT = COkHttpClient.class;
    public static final Class STORE = PrintStore.class;
    public static final Class COOKIDGENERATOR = NoCookieGenerator.class;
    public static final Class<? extends MapGenerator> HEADERGENERATOR = NoHeaderGenerator.class;

    public static final Integer DEFAULT_TASK_RETRY = 0;
    public static final Integer DEFAULT_TASK_DEEP = 0;

    public static final Integer DEFAULT_QUEUE_CALACITY = 11;
    public static final Class<? extends IExecutersListener> DEFAULT_EXECUTERSLISTENER = DefaultExecutersListener.class;
}