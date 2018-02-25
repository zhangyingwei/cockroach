package com.zhangyingwei.cockroach.common;

import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.task.Task;

import java.util.UUID;

/**
 * Created by zhangyw on 2017/12/19.
 */
public class CookieGeneratorTest implements StringGenerator {

    @Override
    public String get(Task task) {
        String cookie = "v="+ UUID.randomUUID().toString();
        System.out.println(cookie);
        return cookie;
    }
}