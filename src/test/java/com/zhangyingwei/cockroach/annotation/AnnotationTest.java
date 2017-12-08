package com.zhangyingwei.cockroach.annotation;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.DescribeStore;
import com.zhangyingwei.cockroach.store.TestStore;

/**
 * Created by zhangyw on 2017/12/8.
 */

@EnableAutoConfiguration
//@AppName("hello spider")
@Store(DescribeStore.class)
//@AutoClose(true)
@ThreadConfig(num = 3,sleep = 5000)
//@CookieConfig("asdfasdfasdfasdfasfasdfa")
//@HttpHeaderConfig({
//        "a=a",
//        "b=b",
//        "c=c"
//})
//@ProxyConfig("1.1.1.1,2.2.2.2")
public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        TaskQueue queue = TaskQueue.of();
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        queue.push(new Task("http://zhangyingwei.com"));
        CockroachApplication.run(AnnotationTest.class,queue);
    }
}
