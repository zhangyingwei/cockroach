package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Forbidden/禁止
 */
public class Http403Exception extends Http40XException  {
    public Http403Exception() {
    }

    public Http403Exception(String message) {
        super(message);
    }

    public Http403Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http403Exception(Throwable cause) {
        super(cause);
    }

    public Http403Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
