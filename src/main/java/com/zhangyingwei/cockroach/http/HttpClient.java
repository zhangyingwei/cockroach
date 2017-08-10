package com.zhangyingwei.cockroach.http;

import com.zhangyingwei.cockroach.executer.TaskContext;
import com.zhangyingwei.cockroach.executer.TaskResponse;

import java.util.Map;

/**
 * Created by zhangyw on 2017/8/10.
 */
public interface HttpClient {
    TaskResponse doGet(TaskContext context) throws Exception;
    TaskResponse doPost(TaskContext context) throws Exception;
}
