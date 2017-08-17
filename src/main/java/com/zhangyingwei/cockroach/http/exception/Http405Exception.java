package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Method Not Allowed/方法未允许
 */
public class Http405Exception extends Http40XException  {
    public Http405Exception() {
    }

    public Http405Exception(String message) {
        super(message);
    }

    public Http405Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http405Exception(Throwable cause) {
        super(cause);
    }

    public Http405Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
