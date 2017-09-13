package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.executer.TaskExecuter;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/8/11.
 * Update by zhangye on 2017/9/13
 * 名称生成类 根据递增序列规则
 */
public class NameUtils {
    private static Logger logger = Logger.getLogger(NameUtils.class);
    private static Map<String, Integer> nameMap = new HashMap<>();
    private static Map<String, String> idMap = new HashMap<>();

    public synchronized static String name(Class clazz){
        String name = clazz.getSimpleName();
        Integer index = nameMap.getOrDefault(name, 1);
        nameMap.put(name, index + 1);
        return bulidName(name,index);
    }

    public synchronized static String id(Class clazz){
        String name = clazz.getSimpleName();
        return idMap.getOrDefault(name, UUID.randomUUID().toString());
    }

    private static String bulidName(String name, Integer index) {
        return name + "-" + index;
    }

    public static void main(String[] args) {
        logger.info(NameUtils.name(TaskExecuter.class));
//        System.out.println(NameUtils.name(TaskExecuter.class));
//        System.out.println(NameUtils.name(TaskExecuter.class));
//        System.out.println(NameUtils.name(TaskExecuter.class));
    }
}
