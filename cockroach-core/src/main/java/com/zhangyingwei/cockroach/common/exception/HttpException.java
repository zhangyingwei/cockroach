package com.zhangyingwei.cockroach.common.exception;

import com.zhangyingwei.cockroach.common.utils.CockroachUtils;

/**
 * Created by zhangyw on 2017/8/17.
 * Multiple Choices/多重选择
 */
public class HttpException extends Exception  {
    public HttpException() {
    }

    public HttpException(String message,int code) {
        super(CockroachUtils.exceptionMessage(code,message));
    }

    public HttpException(String message,int code, Throwable cause) {
        super(CockroachUtils.exceptionMessage(code,message), cause);
    }
}
