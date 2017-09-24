package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Multiple Choices/多重选择
 */
public class Http300Exception extends Http30XException  {
    private static final int CODE = 300;
    public Http300Exception() {
    }

    public Http300Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http300Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http300Exception(Throwable cause) {
        super(cause);
    }

    public Http300Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
