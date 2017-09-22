package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Bad Request/错误请求
 */
public class Http401Exception extends Http40XException  {
    private static final int CODE = 401;
    public Http401Exception() {
    }

    public Http401Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http401Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http401Exception(Throwable cause) {
        super(cause);
    }

    public Http401Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
