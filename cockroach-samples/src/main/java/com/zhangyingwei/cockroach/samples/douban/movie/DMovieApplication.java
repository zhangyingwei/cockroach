package com.zhangyingwei.cockroach.samples.douban.movie;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
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
@ThreadConfig(num = 1,sleep = 500)
@Store(DMovieStore.class)
public class DMovieApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("type", "movie");
        params.put("tag", "热门");
        params.put("page_limit", 50);
        params.put("page_start", 0);
        Task task = new Task("https://movie.douban.com/j/search_subjects", "douban.movie", params);
        queue.push(task);
        CockroachApplication.run(DMovieApplication.class, queue);
    }
}
