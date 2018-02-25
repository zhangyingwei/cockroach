package com.zhangyingwei.cockroach.annotation;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.common.CookieGeneratorTest;
import com.zhangyingwei.cockroach.common.HeaderGeneratorTest;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.TestStore;

/**
 * Created by zhangyw on 2017/12/8.
 */

@EnableAutoConfiguration
//@AppName("hello spider")
@Store(TestStore.class)
//@AutoClose(true)
@ThreadConfig(num = 1)
@CookieConfig(value = "asdfasdfasdfasdfasfasdfa",cookieGenerator = CookieGeneratorTest.class)
@HttpHeaderConfig(headerGenerator = HeaderGeneratorTest.class)
//@HttpHeaderConfig({
//        "a=a",
//        "b=b",
//        "c=c"
//})
//@ProxyConfig("1.1.1.1,2.2.2.2")
public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of(20);
        queue.push(new Task("http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=010000&industrytype=32&keyword=Java%E5%BC%80%E5%8F%91&keywordtype=2&lang=c&stype=2&postchannel=0000&fromType=1&confirmdate=9"));
        CockroachApplication.run(AnnotationTest.class,queue);
    }
}
