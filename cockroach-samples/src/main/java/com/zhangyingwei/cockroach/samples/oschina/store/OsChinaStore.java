package com.zhangyingwei.cockroach.samples.oschina.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by zhangyw on 2018/2/26.
 */
public class OsChinaStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("oschina.blog")) {
            response.select(".item").forEach(item -> {
                String href = item.select(".blog-title-link").attr("href");
                try {
                    response.getQueue().push(new Task(href,"oschina.blog.item"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else if (response.isGroup("oschina.blog.item")) {
            Elements content = response.select(".blog-content");
            String title = content.select(".title").text().replace("原 荐 ","");
            String autor = content.select(".name").text();
            System.out.println(String.format("文章标题: %s  作者: %s",title,autor));
        }
    }
}
