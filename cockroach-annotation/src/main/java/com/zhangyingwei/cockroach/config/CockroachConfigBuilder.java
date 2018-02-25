package com.zhangyingwei.cockroach.config;

import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.listener.IExecutersListener;
import com.zhangyingwei.cockroach.executer.response.filter.ITaskResponseFilter;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhangyw on 2017/12/8.
 */
public class CockroachConfigBuilder {
    private Annotation[] annotations;
    private CockroachConfig config;

    public CockroachConfigBuilder(Annotation[] annotations) {
        this.config = new CockroachConfig();
        this.annotations = annotations;
    }

    public CockroachConfig bulid() throws Exception {
        if(this.isAutoConfiguration()){
            this.autoConfig();
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof AppName) {
                this.config.setAppName(((AppName) annotation).value());
            } else if (annotation instanceof ProxyConfig) {
                this.config.setProxys(((ProxyConfig) annotation).value());
            } else if (annotation instanceof AutoClose) {
                this.config.setAutoClose(((AutoClose) annotation).value());
            } else if (annotation instanceof ThreadConfig) {
                this.config.setThread(((ThreadConfig) annotation).num(), ((ThreadConfig) annotation).sleep());
            } else if (annotation instanceof HttpConfig) {
                this.config.setHttpClient(((HttpConfig) annotation).value());
                this.config.setShowHttpClientProgress(((HttpConfig) annotation).progress());
            } else if (annotation instanceof Store) {
                this.config.setStore(((Store) annotation).value());
            } else if (annotation instanceof CookieConfig) {
                this.config.setCookie(((CookieConfig) annotation).value());
                this.config.setCookieGenerator(((CookieConfig) annotation).cookieGenerator());
            } else if (annotation instanceof HttpHeaderConfig) {
                String[] headers = ((HttpHeaderConfig) annotation).value();
                this.config.setHeaderGenerator(((HttpHeaderConfig) annotation).headerGenerator());
                if (headers.length > 0) {
                    for (String header : headers) {
                        if (header.indexOf("=") < 0) {
                            throw new Exception("require header like key=value, but get "+header);
                        }
                        String[] kv = header.split("=");
                        this.config.addHttpHeader(kv[0], kv[1]);
                    }
                }
            } else if (annotation instanceof TaskErrorHandlerConfig) {
                this.config.setTaskErrorHandler(((TaskErrorHandlerConfig) annotation).value());
            } else if (annotation instanceof TaskResponseFiltersConfig) {
                Set<Class<? extends ITaskResponseFilter>> filters = new HashSet<Class<? extends ITaskResponseFilter>>();
                Class[] values = ((TaskResponseFiltersConfig) annotation).value();
                if (values != null && values.length > 0) {
                    for (Class value : values) {
                        filters.add(value);
                    }
                }
                this.config.setResponseFilters(filters);
            } else if (annotation instanceof ExecutersListener) {
                Class<? extends IExecutersListener> listener = ((ExecutersListener) annotation).value();
                this.config.setExecutersListener(listener);
            }
        }
        return this.config;
    }

    /**
     * 判断是否配置 autoconfiguration
     * @return
     */
    private boolean isAutoConfiguration() {
        for (Annotation annotation : this.annotations) {
            if (annotation instanceof EnableAutoConfiguration) {
                return true;
            }
        }
        return false;
    }

    private void autoConfig() {
        this.config
                .setAppName(Constants.DEFAULT_APP_NAME)
                .setAutoClose(Constants.DEFAULT_AUTO_CLOSE)
                .setTaskErrorHandler(Constants.DEFAULT_TASKERROR_HANDLER)
                .setThread(Constants.DEFAULT_THREAD_NUM, Constants.DEFAULT_THREAD_SLEEP);
    }
}
