package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import org.jsoup.select.Elements;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/9/18.
 */
public class ZhiHuMeiZhi implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        System.out.println("store"+response.getTask());
        if (!response.getTask().getGroup().equals("image")) {
            CockroachQueue queue = response.getQueue();
            Elements as = response.select("div").select("img");
            as.stream().forEach(element -> {
                Task task = new Task(element.attr("data-original"));
                task.setGroup("image");
                try {
                    queue.push(task);
                    System.out.println("push:" + task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            File file = new File("D://zhihumeizitu2/"+ UUID.randomUUID()+".jpg");
            byte[] bytes = response.getContentBytes();
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
            outputStream.write(bytes);
            outputStream.close();
        }
    }
}
