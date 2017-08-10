package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.cockroach.http.COkHttpClient;
import com.zhangyingwei.cockroach.store.PrintStore;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContextTest {
    public static void main(String[] args) {
        CockroachConfig config = new CockroachConfig()
                .setAppName("haha")
                .setThread(2)
                .setHttpClient(new COkHttpClient())
                .setStore(new PrintStore());
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = new TaskQueue();
        queue.push(new ArrayList<String>(){
            {
                add("http://zhangyingwei.com");
            }
        });
        context.start(queue);
    }
}