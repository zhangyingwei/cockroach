package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.PrintStore;
import com.zhangyingwei.cockroach.store.SelecterTestStore;
import org.junit.Test;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContextErrorTest {
    @Test
    public void test() {}
    public static void main(String[] args) throws Exception {
        CockroachConfig config = new CockroachConfig()
                .setAppName("test error")
                .setThread(1)
                .setAutoClose(true)
                .setStore(PrintStore.class);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = TaskQueue.of();
        queue.push(new Task("https://www.123123.com"));
        context.start(queue);
    }
}