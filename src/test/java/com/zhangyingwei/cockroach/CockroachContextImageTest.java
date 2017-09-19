package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.ImageStore;
import com.zhangyingwei.cockroach.store.IpStore;
import com.zhangyingwei.cockroach.store.MeiZhiStore;
import com.zhangyingwei.cockroach.store.ZhiHuMeiZhi;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContextImageTest {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, InstantiationException, IllegalAccessException {
        CockroachConfig config = new CockroachConfig()
                .setAppName("妹子下载器")
                .setThread(20, 3000)
                .setStore(ZhiHuMeiZhi.class)
                .setAutoClose(true);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = TaskQueue.of();
        new Thread(() -> {
            int index = 1;
            while(true){
                try {
                    Task task = new Task("https://www.zhihu.com/collection/61633672?page="+index++);
                    queue.push(task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(index > 199){
                    break;
                }
            }
        }).start();
        context.start(queue);
    }
}