package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.COkHttpClient;
import com.zhangyingwei.cockroach.store.PrintStore;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuterTest {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue queue = new TaskQueue();
//        queue.push(new Task("http://zhangyingwei.com"));
//        queue.push(new Task("http://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl/1.9.13"));
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new TaskExecuter(queue,new COkHttpClient(),new PrintStore()));
        service.execute(new TaskExecuter(queue,new COkHttpClient(),new PrintStore()));
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