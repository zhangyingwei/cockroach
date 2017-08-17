package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Multiple Choices/多重选择
 */
public class Http30XException extends HttpException  {
    public Http30XException() {
    }

    public Http30XException(String message) {
        super(message);
    }

    public Http30XException(String message, Throwable cause) {
        super(message, cause);
    }

    public Http30XException(Throwable cause) {
        super(cause);
    }

    public Http30XException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
