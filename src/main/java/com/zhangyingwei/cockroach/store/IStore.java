package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.TaskResponse;

/**
 * Created by zhangyw on 2017/8/10.
 */
public interface IStore {
    void store(TaskResponse response);
}
