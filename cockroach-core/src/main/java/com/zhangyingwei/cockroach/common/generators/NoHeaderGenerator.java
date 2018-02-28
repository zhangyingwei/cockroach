package com.zhangyingwei.cockroach.common.generators;

import com.zhangyingwei.cockroach.executer.task.Task;

import java.util.Map;

/**
 * Created by zhangyw on 2017/12/19.
 */
public class NoHeaderGenerator implements MapGenerator {
    @Override
    public Map get(Task task) {
        return null;
    }
}
