package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;

import java.io.*;

/**
 * Created by zhangyw on 2017/9/18.
 */
public class ImageStore implements IStore {

    @Override
    public void store(TaskResponse response) throws Exception {
        byte[] bytes = response.getContent().bytes();
        File file = new File("image.jpeg");
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
        outputStream.write(bytes);
        outputStream.close();
    }
}
