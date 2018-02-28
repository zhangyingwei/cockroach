package com.zhangyingwei.cockroach.executer.response.filter;


import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.TaskResponseFiltersConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;

/**
 * @author: zhangyw
 * @date: 2018/1/24
 * @time: 下午3:37
 * @desc:
 */
@EnableAutoConfiguration
@AppName("test")
@TaskResponseFiltersConfig({
    ResponseFilterTest.class
})
public class ITaskResponseFilterTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("https://baidu.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        CockroachApplication.run(ITaskResponseFilterTest.class, queue);
    }
}