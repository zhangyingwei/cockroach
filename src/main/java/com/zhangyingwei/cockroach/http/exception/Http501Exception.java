package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Not Implemented/未实现
 */
public class Http501Exception extends Http50XException  {
    public Http501Exception() {
    }

    public Http501Exception(String message) {
        super(message);
    }

    public Http501Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http501Exception(Throwable cause) {
        super(cause);
    }

    public Http501Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
