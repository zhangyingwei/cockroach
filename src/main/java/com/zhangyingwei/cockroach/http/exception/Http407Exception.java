package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * ProxyConfig Authentication Required/代理服务器认证要求
 */
public class Http407Exception extends Http40XException  {
    private static final int CODE = 407;
    public Http407Exception() {
    }

    public Http407Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http407Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http407Exception(Throwable cause) {
        super(cause);
    }

    public Http407Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
