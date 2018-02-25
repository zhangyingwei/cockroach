package com.zhangyingwei.cockroach.annotation;

import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;

import java.lang.annotation.*;

/**
 * Created by zhangyw on 2017/12/8.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TaskErrorHandlerConfig {
    Class value() default DefaultTaskErrorHandler.class;
}
