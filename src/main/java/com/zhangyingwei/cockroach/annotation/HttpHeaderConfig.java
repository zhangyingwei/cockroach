package com.zhangyingwei.cockroach.annotation;

import java.lang.annotation.*;
import java.util.List;

/**
 * Created by zhangyw on 2017/12/8.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HttpHeaderConfig {
    String[] value();
}
