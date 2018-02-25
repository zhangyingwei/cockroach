package com.zhangyingwei.cockroach.common.utils;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.HttpConfig;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
        queue.push(new Task("http://util.zhangyingwei.com//cockroach/1/carbon.png"));
        queue.push(new Task("http://img.dmc.csdn.net/B3DF79B6065EC826F2EC278369F31F6E.jpg"));
        CockroachApplication.run(FileUtilsTest.class,queue);
    }

    @Test
    public void getOrCreateTest() throws IOException {
        File file = FileUtils.openOrCreate("./", "hello.txt");
        FileUtils.clearFile(file);
        for (int i = 0; i < 10; i++) {
            FileUtils.append(file,i+"\n");
        }
        FileUtils.closeWriters();
        Assert.assertTrue(FileUtils.delete(file));
    }
}