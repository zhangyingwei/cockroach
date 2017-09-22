package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Not Found/未找到
 */
public class Http404Exception extends Http40XException {
    private static final int CODE = 404;
    public Http404Exception() {
    }

    public Http404Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http404Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http404Exception(Throwable cause) {
        super(cause);
    }

    public Http404Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
