package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.task.Task;
import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:32
 * @desc:
 */
public class DefaultQueueTaskDeepTest {
    @Test
    public void accept() throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("1").addDeep(1));
        queue.push(new Task("2").addDeep(3));
        queue.push(new Task("3").addDeep(5));
        queue.push(new Task("4").addDeep(2));
        queue.push(new Task("5").addDeep(4));
        queue.push(new Task("6").addDeep(1));
        queue.push(new Task("7").addDeep(1));

        for (int i = 0; i < 7; i++) {
            queue.take();
        }
    }

    @Test
    public void queueDeepTest() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>(10);
        for (int i = 0; i < 100; i++) {
            queue.put(i);
        }
        System.out.println(queue.size());
    }
}