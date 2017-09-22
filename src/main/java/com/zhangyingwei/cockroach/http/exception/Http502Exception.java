package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Bad Gateway/错误的网关
 */
public class Http502Exception extends Http50XException  {
    private static final int CODE = 502;
    public Http502Exception() {
    }

    public Http502Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http502Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http502Exception(Throwable cause) {
        super(cause);
    }

    public Http502Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
