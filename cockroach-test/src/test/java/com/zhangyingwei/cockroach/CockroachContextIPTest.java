package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.store.IpStore;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContextIPTest {
    public static void main(String[] args) throws Exception {
        CockroachConfig config = new CockroachConfig()
                .setAppName("haha")
                .setThread(5,5000)
                .setProxys("183.222.102.105,183.222.102.108,183.222.102.107,183.222.102.106,183.222.102.104,183.222.102.109")
                .setHttpClient(COkHttpClient.class)
                .setStore(IpStore.class)
                .setAutoClose(true);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = TaskQueue.of();

        new Thread(() -> {
            int i = 167;
            while(true){
                i++;
                try {
//                    Thread.sleep(1000);
                    String url = "http://www.xicidaili.com/wt/"+i;
                    queue.push(new Task(url));
//                    System.out.println("push "+url);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i > 200) {
                    break;
                }
            }
        }).start();

        context.start(queue);

    }
}