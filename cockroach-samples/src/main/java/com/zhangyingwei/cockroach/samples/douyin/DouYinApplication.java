package com.zhangyingwei.cockroach.samples.douyin;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.samples.douyin.store.DouYinStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2018/5/25.
 */
@EnableAutoConfiguration
@AppName("抖音视频")
@ThreadConfig(num = 1,sleep = 1000)
@Store(DouYinStore.class)
@AutoClose(false)
public class DouYinApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        Task task = new Task("https://www.iesdouyin.com/share/video/6546769659985661192", "douyin.vedio");
        queue.push(task);
        CockroachApplication.run(DouYinApplication.class, queue);
    }
}