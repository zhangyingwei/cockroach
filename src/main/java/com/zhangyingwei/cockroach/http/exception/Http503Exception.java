package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Service Unavailable/服务无法获得
 */
public class Http503Exception extends Http50XException  {
    private static final int CODE = 503;
    public Http503Exception() {
    }

    public Http503Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http503Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http503Exception(Throwable cause) {
        super(cause);
    }

    public Http503Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
