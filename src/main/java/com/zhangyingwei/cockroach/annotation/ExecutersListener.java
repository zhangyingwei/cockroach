package com.zhangyingwei.cockroach.annotation;

import java.lang.annotation.*;

/**
 * @author: zhangyw
 * @date: 2018/2/1
 * @time: 下午10:10
 * @desc:
 */
@Documented
@Target( {ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutersListener {
    Class value();
}
