package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;

/**
 * Created by zhangyw on 2017/12/25.
 */
@EnableAutoConfiguration
public class DefaultTaskErrorHandlerTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("https://google.com"));
        CockroachApplication.run(DefaultTaskErrorHandler.class,queue);
    }
}