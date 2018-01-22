package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.AutoClose;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.SelecterTestStore;

/**
 * Created by zhangyw on 2017/8/10.
 */
@EnableAutoConfiguration
@AppName("test spider")
@ThreadConfig(num = 1,sleep = 1000)
@AutoClose(false)
public class CockroachContextQueueRetryTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        for (int i = 0; i < 5; i++) {
            queue.push(new Task("http://hello.com"+i).retry(5));
        }
        CockroachApplication.run(CockroachContextQueueRetryTest.class,queue);
    }
}