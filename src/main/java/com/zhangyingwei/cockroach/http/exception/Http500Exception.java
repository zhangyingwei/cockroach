package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Internal Server Error/内部服务器错误
 */
public class Http500Exception extends Http50XException  {
    public Http500Exception() {
    }

    public Http500Exception(String message) {
        super(message);
    }

    public Http500Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http500Exception(Throwable cause) {
        super(cause);
    }

    public Http500Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
