package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.store.IpStore;

import java.io.FileNotFoundException;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContextIPTest {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, InstantiationException, IllegalAccessException {
        CockroachConfig config = new CockroachConfig()
                .setAppName("haha")
                .setThread(2)
                .setProxys("183.222.102.105,183.222.102.108,183.222.102.107,183.222.102.106,183.222.102.104,183.222.102.109")
                .setHttpClient(COkHttpClient.class)
                .setStore(IpStore.class);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = TaskQueue.of();
        context.start(queue);

        new Thread(() -> {
            int i = 167;
            while(true){
                i++;
                try {
                    Thread.sleep(1000);
                    String url = "http://www.xicidaili.com/wt/"+i;
                    System.out.println(url);
                    queue.push(new Task(url));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i > 1000) {
                    break;
                }
            }
        }).start();


    }
}