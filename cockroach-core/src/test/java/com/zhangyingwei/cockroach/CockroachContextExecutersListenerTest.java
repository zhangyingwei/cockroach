package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.AutoClose;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.PrintTestStore;

/**
 * @author: zhangyw
 * @date: 2018/2/1
 * @time: 下午10:17
 * @desc:
 */
@EnableAutoConfiguration
@AppName("listener test")
//@ExecutersListener()
@Store(PrintTestStore.class)
@AutoClose(true)
public class CockroachContextExecutersListenerTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        for (int i = 0; i < 10; i++) {
            queue.push(new Task("http://baidu.com/?"+i));
        }
        CockroachApplication.run(CockroachContextExecutersListenerTest.class, queue);
    }
}
