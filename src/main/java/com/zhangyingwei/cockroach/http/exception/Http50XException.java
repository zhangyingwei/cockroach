package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Multiple Choices/多重选择
 */
public class Http50XException extends HttpException  {
    public Http50XException() {
    }

    public Http50XException(String message) {
        super(message);
    }

    public Http50XException(String message, Throwable cause) {
        super(message, cause);
    }

    public Http50XException(Throwable cause) {
        super(cause);
    }

    public Http50XException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
