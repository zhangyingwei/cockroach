package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Gateway Timeout/网关超时
 */
public class Http504Exception extends Http50XException  {
    public Http504Exception() {
    }

    public Http504Exception(String message) {
        super(message);
    }

    public Http504Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http504Exception(Throwable cause) {
        super(cause);
    }

    public Http504Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
