package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import org.junit.Test;

/**
 * Created by zhangyw on 2017/8/10.
 */
@EnableAutoConfiguration
@AppName("proxy test")
@ProxyConfig("183.222.102.105,183.222.102.108,183.222.102.107,183.222.102.106,183.222.102.104,183.222.102.109")
@ThreadConfig(num = 1, sleep = 2000)
@AutoClose(false)
public class CockroachContextIPProxyTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        for (int i = 0; i < 100; i++) {
            queue.push(new Task("https://www.google.com.hk/"+i));
        }
        CockroachApplication.run(CockroachContextIPProxyTest.class, queue);
    }

    @Test
    public void trest() {}
}