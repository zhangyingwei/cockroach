package com.zhangyingwei.cockroach.common.generators;

import com.zhangyingwei.cockroach.executer.TaskResponse;

/**
 * Created by zhangyw on 2017/12/12.
 */
public interface NameGenerator {
    String name(TaskResponse response);
}
