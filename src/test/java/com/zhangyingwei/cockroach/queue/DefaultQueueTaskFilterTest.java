package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.Task;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:32
 * @desc:
 */
public class DefaultQueueTaskFilterTest {
    @Test
    public void accept() throws Exception {
        CockroachQueue queue = TaskQueue.of().filter(new TestQueueTaskFilter());
        queue.push(new Task(null));
        queue.push(new Task("http://baidu.com"));
        queue.push(new Task("https://google.com"));
    }
}