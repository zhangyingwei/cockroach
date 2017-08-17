package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Not Found/未找到
 */
public class Http404Exception extends Http40XException  {
    public Http404Exception() {
    }

    public Http404Exception(String message) {
        super(message);
    }

    public Http404Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http404Exception(Throwable cause) {
        super(cause);
    }

    public Http404Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
