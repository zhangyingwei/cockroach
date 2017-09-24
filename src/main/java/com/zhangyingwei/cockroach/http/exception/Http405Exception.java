package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Method Not Allowed/方法未允许
 */
public class Http405Exception extends Http40XException  {
    private static final int CODE = 405;
    public Http405Exception() {
    }

    public Http405Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http405Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http405Exception(Throwable cause) {
        super(cause);
    }

    public Http405Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
