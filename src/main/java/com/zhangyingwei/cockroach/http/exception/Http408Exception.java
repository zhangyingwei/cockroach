package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Request Timeout/请求超时
 */
public class Http408Exception extends Http40XException  {
    private static final int CODE = 408;
    public Http408Exception() {
    }

    public Http408Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http408Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http408Exception(Throwable cause) {
        super(cause);
    }

    public Http408Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
