package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.DescribeStore;
import com.zhangyingwei.cockroach.store.PrintStore;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuterTest {

    public static void  main(String[] args) throws InterruptedException {
        TaskQueue queue = TaskQueue.of();
        queue.push(new Task("http://zhangyingwei.com"));
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new TaskExecuter(queue,new COkHttpClient(),new DescribeStore(),new DefaultTaskErrorHandler(),0,true));
    }
}