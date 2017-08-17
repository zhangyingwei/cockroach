package com.zhangyingwei.cockroach.http.exception;

/**
 * Created by zhangyw on 2017/8/17.
 * Multiple Choices/多重选择
 */
public class Http300Exception extends Http30XException  {
    public Http300Exception() {
    }

    public Http300Exception(String message) {
        super(message);
    }

    public Http300Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Http300Exception(Throwable cause) {
        super(cause);
    }

    public Http300Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
