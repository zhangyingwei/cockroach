package com.zhangyingwei.cockroach.samples.douyin.store;

import com.zhangyingwei.cockroach.common.utils.FileUtils;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2018/5/25.
 */
public class DouYinStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("douyin.vedio")) {
            response.select("script").stream().map(item -> {
                return item.html();
            }).filter(item -> {
                return item != null && item.length() > 0 && item.trim().startsWith("$(function");
            }).forEach(item -> {
                String addr = item.split("playAddr:")[1].split("cover")[0].trim();
                String resAddr = addr.substring(1, addr.length() - 2);
                try {
                    response.getQueue().push(new Task(resAddr,"douyin.vedio.src"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else if (response.isGroup("douyin.vedio.src")) {
            byte[] bytes = response.getContent().bytes();
            FileUtils.save(bytes,"vedios","dy.mp4");
        }
    }
}
