package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import org.jsoup.select.Elements;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/9/18.
 */
public class MeiZhiStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        System.out.println("store"+response.getTask());
        if (!response.getTask().getGroup().equals("image")) {
            CockroachQueue queue = response.getQueue();
            Elements as = response.select(".main-content").select("a").select("img");
            as.stream().forEach(element -> {
                Task task = new Task(element.attr("src"));
                task.setGroup("image");
                try {
                    queue.push(task);
                    System.out.println("push:" + task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            File file = new File("D://meizitu/"+UUID.randomUUID()+".png");
            byte[] bytes = response.getContent().bytes();
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
            outputStream.write(bytes);
            outputStream.close();
        }
    }
}
