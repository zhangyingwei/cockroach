package com.zhangyingwei.cockroach.common.generators;

import com.zhangyingwei.cockroach.executer.task.Task;

/**
 * Created by zhangyw on 2017/12/19.
 */
public interface CockroachGenerator<T> {
    T get(Task task);
}
