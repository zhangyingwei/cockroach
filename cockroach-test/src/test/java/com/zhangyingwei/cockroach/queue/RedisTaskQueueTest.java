package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.filter.IQueueTaskFilter;
import com.zhangyingwei.cockroach.store.DescribeStore;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * Created by zhangyw on 2018/2/27.
 */

@EnableAutoConfiguration
@AppName("redis")
@ThreadConfig(num = 5, sleep = 100)
@AutoClose(false)
@Store(DescribeStore.class)
public class RedisTaskQueueTest {
    private static CockroachQueue queue = RedisTaskQueue.of("172.30.154.75", 6379, "cockroach","cockroach-error");

    public static void main(String[] args) throws Exception {
        CockroachApplication.run(RedisTaskQueueTest.class,queue);
    }

//    @Test
    public void take() throws Exception {
        System.out.println(queue.take());
    }

//    @Test
    public void push() throws Exception {
        queue.filter(new IQueueTaskFilter() {
            @Override
            public boolean accept(Task task) {
                return task.getUrl().contains("zhangyingwei");
            }
        });
        for (int i = 0; i < 100; i++) {
            Task task = new Task("http://blog.zhangyingwei.com","zhangyingwei").retry(10).addDeep(20);
            queue.push(task);
        }
        queue.push(new Task("http://baidu.com"));
    }

//    @Test
    public void test(){
        Task task = new Task("http://baidu.com");
        System.out.println(task);
        JSONObject jsonObject = JSONObject.fromObject(task);
        System.out.println(jsonObject);
        Task task1 = (Task) JSONObject.toBean(jsonObject, Task.class);
        System.out.println(task1);
    }
}
