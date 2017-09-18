package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import com.zhangyingwei.cockroach.store.ZhihuStore;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContextZhihuTest {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, InstantiationException, IllegalAccessException {
        String cookie = "";
        CockroachConfig config = new CockroachConfig()
                .setAppName("haha")
                .setThread(1)
                .setHttpClient(COkHttpClient.class)
//                .setCookie(cookie)
//                .addHttpHeader("authorization","")
                .setStore(ZhihuStore.class);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = TaskQueue.of();
        context.start(queue);
//        queue.push(new Task("https://www.zhihu.com/people/wmhsr/activities"));
        queue.push(new Task("https://www.zhihu.com/api/v4/members/wmhsr/followees",new HashMap(){
            {
                put("include", "data[*].answer_count,articles_count,gender,follower_count,is_followed,is_following,badge[?(type=best_answerer)].topics");
                put("offset", 0);
                put("limit", 100);
            }
        }));
    }
}