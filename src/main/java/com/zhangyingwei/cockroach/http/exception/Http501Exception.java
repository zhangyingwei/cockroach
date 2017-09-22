package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Not Implemented/未实现
 */
public class Http501Exception extends Http50XException  {
    private static final int CODE = 501;
    public Http501Exception() {
    }

    public Http501Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http501Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http501Exception(Throwable cause) {
        super(cause);
    }

    public Http501Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
