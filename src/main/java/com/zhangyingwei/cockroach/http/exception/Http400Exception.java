package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Unauthorized/未授权
 */
public class Http400Exception extends Http40XException  {
    public Http400Exception() {
    }

    public Http400Exception(String message) {
        super(message);
    }

    public Http400Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http400Exception(Throwable cause) {
        super(cause);
    }

    public Http400Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
