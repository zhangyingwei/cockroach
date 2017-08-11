package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.executer.TaskExecuter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class NameUtils {
    private static Map<String, Integer> nameMap = new HashMap<>();

    public static String name(Class clazz){
        String name = clazz.getSimpleName();
        Integer index = nameMap.getOrDefault(name, 0);
        nameMap.put(name, index + 1);
        return bulidName(name,index);
    }

    private static String bulidName(String name, Integer index) {
        return name + "-" + index;
    }

    public static void main(String[] args) {
        System.out.println(NameUtils.name(TaskExecuter.class));
        System.out.println(NameUtils.name(TaskExecuter.class));
        System.out.println(NameUtils.name(TaskExecuter.class));
        System.out.println(NameUtils.name(TaskExecuter.class));
    }
}
