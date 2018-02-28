package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;

/**
 * @author: zhangyw
 * @date: 2018/2/1
 * @time: 下午10:30
 * @desc:
 */
public class PrintTestStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        System.out.println("haha...");
    }
}
