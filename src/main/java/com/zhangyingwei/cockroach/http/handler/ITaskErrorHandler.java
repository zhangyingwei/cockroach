package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.executer.Task;

/**
 * Created by zhangyw on 2017/8/16.
 * 任务失败回调
 */
public interface ITaskErrorHandler {
    void error(Task task,String message);
}
