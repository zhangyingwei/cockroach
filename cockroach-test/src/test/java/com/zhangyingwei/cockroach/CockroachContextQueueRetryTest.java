package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.AutoClose;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;

/**
 * Created by zhangyw on 2017/8/10.
 */
@EnableAutoConfiguration
@AppName("test spider")
@ThreadConfig(num = 1,sleep = 1000)
@AutoClose(true)
public class CockroachContextQueueRetryTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        for (int i = 0; i < 5; i++) {
            queue.push(new Task("http://hello.com"+i).retry(5));
        }
        CockroachApplication.run(CockroachContextQueueRetryTest.class,queue);
    }
}