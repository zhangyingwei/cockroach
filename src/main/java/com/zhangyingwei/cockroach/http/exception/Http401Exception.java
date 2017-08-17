package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Bad Request/错误请求
 */
public class Http401Exception extends Http40XException  {
    public Http401Exception() {
    }

    public Http401Exception(String message) {
        super(message);
    }

    public Http401Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http401Exception(Throwable cause) {
        super(cause);
    }

    public Http401Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
