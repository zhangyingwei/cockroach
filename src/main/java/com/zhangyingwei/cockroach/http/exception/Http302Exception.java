package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * 重定向
 */
public class Http302Exception extends Http30XException  {
    public Http302Exception() {
    }

    public Http302Exception(String message) {
        super(message);
    }

    public Http302Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http302Exception(Throwable cause) {
        super(cause);
    }

    public Http302Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
