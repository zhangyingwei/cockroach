package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Forbidden/禁止
 */
public class Http403Exception extends Http40XException  {
    private static final int CODE = 403;
    public Http403Exception() {
    }

    public Http403Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http403Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http403Exception(Throwable cause) {
        super(cause);
    }

    public Http403Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
