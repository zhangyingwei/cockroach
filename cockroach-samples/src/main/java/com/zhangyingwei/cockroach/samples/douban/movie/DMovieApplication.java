package com.zhangyingwei.cockroach.samples.douban.movie;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.samples.douban.movie.store.DMovieStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2018/2/28.
 */
@EnableAutoConfiguration
@AppName("豆瓣电影")
@ThreadConfig(num = 8,sleep = 100)
@Store(DMovieStore.class)
@AutoClose(false)
public class DMovieApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("sort", "T");
        params.put("range", "0,20");
        params.put("tag", "");
        params.put("start", 0);
        Task task = new Task("https://movie.douban.com/j/new_search_subjects", "douban.movie", params);
        queue.push(task);
        CockroachApplication.run(DMovieApplication.class, queue);
    }
}