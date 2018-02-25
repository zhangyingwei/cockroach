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
public @interface AutoClose {
    boolean value() default Constants.DEFAULT_AUTO_CLOSE;
}
