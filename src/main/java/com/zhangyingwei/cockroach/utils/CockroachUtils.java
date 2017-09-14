package com.zhangyingwei.cockroach.utils;

/**
 * Created by zhangyw on 2017/9/14.
 */
public class CockroachUtils {
    public static void addSystemPropertie(String key,Object value) {
        System.setProperty(key, value + "");
    }
}