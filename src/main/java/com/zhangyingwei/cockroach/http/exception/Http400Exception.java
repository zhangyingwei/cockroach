package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Unauthorized/未授权
 */
public class Http400Exception extends Http40XException  {
    private static final int CODE = 400;
    public Http400Exception() {
    }

    public Http400Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http400Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http400Exception(Throwable cause) {
        super(cause);
    }

    public Http400Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
