package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Not Acceptable/无法访问
 */
public class Http406Exception extends Http40XException  {
    public Http406Exception() {
    }

    public Http406Exception(String message) {
        super(message);
    }

    public Http406Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http406Exception(Throwable cause) {
        super(cause);
    }

    public Http406Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
