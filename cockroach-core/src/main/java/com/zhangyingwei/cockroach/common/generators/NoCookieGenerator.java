package com.zhangyingwei.cockroach.common.generators;

import com.zhangyingwei.cockroach.executer.task.Task;

/**
 * Created by zhangyw on 2017/12/19.
 */
public class NoCookieGenerator implements StringGenerator {
    @Override
    public String get(Task task) {
        return null;
    }
}
