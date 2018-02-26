package com.zhangyingwei.cockroach.samples.oschina;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.samples.oschina.store.OsChinaStore;

/**
 * Created by zhangyw on 2018/2/26.
 */

@EnableAutoConfiguration
@AppName("开源中国博客爬虫")
@ThreadConfig(num = 10,sleep = 500)
@Store(OsChinaStore.class)
public class OschinaApplicatoin {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        for (int i = 1; i <= 10; i++) {
            queue.push(new Task("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p="+i,"oschina.blog").retry(5));
        }
        CockroachApplication.run(OschinaApplicatoin.class, queue);
    }
}
