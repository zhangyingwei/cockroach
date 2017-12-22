package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import java.lang.annotation.Annotation;

/**
 * Created by zhangyw on 2017/12/8.
 */
public class AnnotationUtils {
    public static boolean isAutoConfiguration(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof EnableAutoConfiguration) {
                return true;
            }
        }
        return false;
    }
}
