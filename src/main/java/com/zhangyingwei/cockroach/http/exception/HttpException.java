package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Multiple Choices/多重选择
 */
public class HttpException extends Exception  {
    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
