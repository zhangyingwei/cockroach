package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.PrintStore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuterTest {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue queue = TaskQueue.of();
//        queue.push(new Task("http://zhangyingwei.com"));
//        queue.push(new Task("http://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl/1.9.13"));
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new TaskExecuter(queue,new COkHttpClient(),new PrintStore(),new DefaultTaskErrorHandler(),0,true));
        service.execute(new TaskExecuter(queue,new COkHttpClient(),new PrintStore(),new DefaultTaskErrorHandler(),0,true));
        service.execute(() -> {
            while(true){
                try {
                    queue.push(new Task("http://zhangyingwei.com"));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}