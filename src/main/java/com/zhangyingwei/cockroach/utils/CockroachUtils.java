package com.zhangyingwei.cockroach.utils;

/**
 * Created by zhangyw on 2017/9/14.
 * @author zhangyw
 */
public class CockroachUtils {
    public static void addSystemPropertie(String key,Object value) {
        System.setProperty(key, value + "");
    }

    public static String exceptionMessage(int code,String message){
        return String.format("code:%d - message:%s",code,message);
    }
}