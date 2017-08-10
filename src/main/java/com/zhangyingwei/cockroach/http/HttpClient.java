package com.zhangyingwei.cockroach.http;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;

/**
 * Created by zhangyw on 2017/8/10.
 */
public interface HttpClient {
    TaskResponse doGet(Task task) throws Exception;
    TaskResponse doPost(Task task) throws Exception;
}
