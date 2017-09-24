package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * HTTP Version Not Supported/不支持的 HTTP 版本
 */
public class Http505Exception extends Http50XException  {
    private static final int CODE = 505;
    public Http505Exception() {
    }

    public Http505Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http505Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http505Exception(Throwable cause) {
        super(cause);
    }

    public Http505Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
