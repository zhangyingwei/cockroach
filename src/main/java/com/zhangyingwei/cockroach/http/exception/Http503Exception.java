package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Service Unavailable/服务无法获得
 */
public class Http503Exception extends Http50XException  {
    public Http503Exception() {
    }

    public Http503Exception(String message) {
        super(message);
    }

    public Http503Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http503Exception(Throwable cause) {
        super(cause);
    }

    public Http503Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
