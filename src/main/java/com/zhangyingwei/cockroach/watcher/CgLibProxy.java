package com.zhangyingwei.cockroach.watcher;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: zhangyw
 * @date: 2018/1/21
 * @time: 下午6:40
 * @desc:
 */
public class CgLibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
        System.out.println(method.getName());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
        return o1;
    }
}
