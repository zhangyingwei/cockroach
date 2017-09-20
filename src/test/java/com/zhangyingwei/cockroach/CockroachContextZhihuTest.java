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
        String cockie = "_zap=810b4319-e342-44b7-91f9-be9ae9746292; d_c0=\"AICCk7TeAQyPTsHcD0WF501PiT90bJADrA4=|1499063919\"; aliyungf_tc=AQAAACtqlm4T3gwAygQCdwBeiij7Tk3X; q_c1=26d50d63a92c493bbfa32bd0a522e368|1504513071000|1498628398000; capsion_ticket=\"2|1:0|10:1504515868|14:capsion_ticket|44:ZDE5MGNhNGFiMzdjNGJlZjkwYjdlNzY1YzEwODFmNjI=|01584a703049547c1463954a69955e28e2f23d365c4f3a24cd129b5e06e5502f\"; l_cap_id=\"ZTM5M2VjNGQ5YTVhNDlkYmE4N2U4YzcyYWY1MTRhMGE=|1505121546|773d7442201eadff2d0e4004de916d0ff1d0bedb\"; r_cap_id=\"ZDJlZjYzZGZlYmFlNDI4MDliMTFmYmU3NDcwYTA1NzA=|1505121546|25ee42b66d37c9f2e6bd488d82f47139e6b3838d\"; cap_id=\"YTQzOTk2ZWEzNGRkNDAxM2IyNjYxOWJkODI0ZmEwNDU=|1505121546|bb86d6219dc11da1ab478c8e65830299d61f850b\"; z_c0=\"2|1:0|10:1505121583|4:z_c0|92:Mi4xSzZDZkFBQUFBQUFBZ0lLVHRONEJEQ2NBQUFDRUFsVk5MLUxkV1FENVVnWWUwWGoteW82WmVaVmRNZ24yYkZqOUdB|31ffd2640f3e3090d9244cd09551189e9d2a5bd6df3b0ef108d02abf6b4c9695\"; q_c1=26d50d63a92c493bbfa32bd0a522e368|1505121608000|1498628398000; s-q=%E8%83%B8; s-i=3; sid=2tit5cho; __utma=155987696.88982080.1505794463.1505794463.1505794463.1; __utmc=155987696; __utmz=155987696.1505794463.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _xsrf=31d4202f-5216-4dd8-a0fa-4feed929f1ce";
        CockroachConfig config = new CockroachConfig()
                .setAppName("haha")
                .setThread(1)
                .setHttpClient(COkHttpClient.class)
                .setCookie(cockie)
                .setAutoClose(true)
                .addHttpHeader("Host","www.zhihu.com")
                .addHttpHeader("Upgrade-Insecure-Requests","1")
                .addHttpHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .setStore(ZhihuStore.class);
        CockroachContext context = new CockroachContext(config);
        TaskQueue queue = TaskQueue.of();
//        queue.push(new Task("https://www.zhihu.com/people/wmhsr/activities"));
        queue.push(new Task("https://www.zhihu.com/api/v4/members/excited-vczh/followees?offset=0&limit=20"));
        context.start(queue);
    }
}