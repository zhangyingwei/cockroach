package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Request Timeout/请求超时
 */
public class Http408Exception extends Http40XException  {
    public Http408Exception() {
    }

    public Http408Exception(String message) {
        super(message);
    }

    public Http408Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http408Exception(Throwable cause) {
        super(cause);
    }

    public Http408Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
