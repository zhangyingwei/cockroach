package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.executer.task.Task;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by zhangyw on 2017/9/20.
 */
public class TaskTest {
    @Test
    public void getUrl() throws Exception {
        Task task = new Task("http://zhangyingwei.com");
        task.setParams(new HashMap(){
            {
                put("key", "hello");
                put("value", "nihao");
            }
        });
        System.out.println(task.getUrl());
    }
}