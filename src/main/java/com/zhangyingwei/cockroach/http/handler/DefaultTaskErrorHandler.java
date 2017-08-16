package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.executer.Task;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class DefaultTaskErrorHandler implements ITaskErrorHandler {
    @Override
    public void error(Task task,String message) {
        System.out.println("task error: "+message);
    }
}
