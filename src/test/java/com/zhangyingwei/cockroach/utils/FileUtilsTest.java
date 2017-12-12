package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.HttpConfig;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.store.IStore;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/12/12.
 */
@EnableAutoConfiguration
@Store(ImageStore.class)
@ThreadConfig(num = 1)
@HttpConfig(progress = true)
public class FileUtilsTest {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("http://mov.bn.netease.com/open-movie/nos/flv/2013/09/11/S97IU0TJ3_sd.flv"));
        CockroachApplication.run(FileUtilsTest.class,queue);
    }
}