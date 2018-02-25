package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;

import java.io.IOException;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class PrintStore implements IStore {
    @Override
    public void store(TaskResponse response) throws IOException {
        System.out.println(response.getContent());
    }
}
