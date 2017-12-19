package com.zhangyingwei.cockroach.annotation;

import com.zhangyingwei.cockroach.common.NoCookieGenerator;
import com.zhangyingwei.cockroach.common.StringGenerator;

import java.lang.annotation.*;

/**
 * Created by zhangyw on 2017/12/8.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CookieConfig {
    String value() default "";
    Class<?> cookieGenerator() default NoCookieGenerator.class;
}
