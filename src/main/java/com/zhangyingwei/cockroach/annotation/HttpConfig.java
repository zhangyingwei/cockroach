package com.zhangyingwei.cockroach.annotation;

import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;

import java.lang.annotation.*;

/**
 * Created by zhangyw on 2017/12/8.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HttpConfig {
    Class value() default COkHttpClient.class;
    boolean progress() default false;
}
