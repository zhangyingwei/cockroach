package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Bad Gateway/错误的网关
 */
public class Http502Exception extends Http50XException  {
    public Http502Exception() {
    }

    public Http502Exception(String message) {
        super(message);
    }

    public Http502Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http502Exception(Throwable cause) {
        super(cause);
    }

    public Http502Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
