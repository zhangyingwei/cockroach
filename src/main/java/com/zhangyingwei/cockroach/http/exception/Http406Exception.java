package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Not Acceptable/无法访问
 */
public class Http406Exception extends Http40XException  {
    private static final int CODE = 406;
    public Http406Exception() {
    }

    public Http406Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http406Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http406Exception(Throwable cause) {
        super(cause);
    }

    public Http406Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
