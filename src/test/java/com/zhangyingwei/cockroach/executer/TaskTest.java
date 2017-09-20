package com.zhangyingwei.cockroach.executer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

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