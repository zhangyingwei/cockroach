package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.TaskResponse;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class PrintStore implements IStore {
    @Override
    public void store(TaskResponse response) {
        System.out.println(response.getContent());
//        System.out.println("getContent");
    }
}
