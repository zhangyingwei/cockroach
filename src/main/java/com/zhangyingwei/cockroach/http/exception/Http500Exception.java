package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Internal Server Error/内部服务器错误
 */
public class Http500Exception extends Http50XException  {
    private static final int CODE = 500;
    public Http500Exception() {
    }

    public Http500Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http500Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http500Exception(Throwable cause) {
        super(cause);
    }

    public Http500Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
