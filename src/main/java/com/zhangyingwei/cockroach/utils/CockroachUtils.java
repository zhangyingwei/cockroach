package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.watcher.CgLibProxy;
import net.sf.cglib.proxy.Enhancer;

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

    public static <T> T createBean(Class clazz){
        CgLibProxy proxy = new CgLibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(proxy);
        return (T) enhancer.create();
    }

    public static <T> T createBean(Class clazz,Class[] argTypes, Object[] args) {
        CgLibProxy proxy = new CgLibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(proxy);
        return (T) enhancer.create(argTypes,args);
    }
}