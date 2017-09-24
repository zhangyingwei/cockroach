package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Gateway Timeout/网关超时
 */
public class Http504Exception extends Http50XException  {
    private static final int CODE = 504;
    public Http504Exception() {
    }

    public Http504Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http504Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http504Exception(Throwable cause) {
        super(cause);
    }

    public Http504Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
