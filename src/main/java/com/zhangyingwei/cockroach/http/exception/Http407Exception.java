package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Proxy Authentication Required/代理服务器认证要求
 */
public class Http407Exception extends Http40XException  {
    public Http407Exception() {
    }

    public Http407Exception(String message) {
        super(message);
    }

    public Http407Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http407Exception(Throwable cause) {
        super(cause);
    }

    public Http407Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
