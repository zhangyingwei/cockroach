package com.zhangyingwei.cockroach.http.exception;

import com.zhangyingwei.cockroach.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Moved Permanently/被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个URI之一
 */
public class Http301Exception extends Http30XException  {
    private static final int CODE = 301;
    public Http301Exception() {
    }

    public Http301Exception(String message) {
        super(CockroachUtils.exceptionMessage(CODE,message));
    }

    public Http301Exception(String message, Throwable cause) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause);
    }

    public Http301Exception(Throwable cause) {
        super(cause);
    }

    public Http301Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(CockroachUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
