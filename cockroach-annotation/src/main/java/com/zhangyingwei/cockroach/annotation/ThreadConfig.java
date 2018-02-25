package com.zhangyingwei.cockroach.annotation;

import com.zhangyingwei.cockroach.config.Constants;

import java.lang.annotation.*;

/**
 * Created by zhangyw on 2017/12/8.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ThreadConfig {
    int num(); //线程数量
    int sleep() default Constants.DEFAULT_THREAD_SLEEP; //线程睡眠时间
}
