package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * HTTP Version Not Supported/不支持的 HTTP 版本
 */
public class Http505Exception extends Http50XException  {
    public Http505Exception() {
    }

    public Http505Exception(String message) {
        super(message);
    }

    public Http505Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http505Exception(Throwable cause) {
        super(cause);
    }

    public Http505Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
