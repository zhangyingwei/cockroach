package com.zhangyingwei.cockroach.samples.juejin;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.AppName;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.cockroach.samples.juejin.store.JueJinStore;

/**
 * @author: zhangyw
 * @date: 2018/2/26
 * @time: 下午8:43
 * @desc:
 */

@EnableAutoConfiguration
@AppName("掘金")
@ThreadConfig(num = 1,sleep = 500)
@Store(JueJinStore.class)
public class JueJinApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("https://timeline-merger-ms.juejin.im/v1/get_entry_by_rank?src=web&uid=58368a0461ff4b475bd600bc&device_id=1519648660286&token=eyJhY2Nlc3NfdG9rZW4iOiJqQVlaSWxIN1U3aGJnV3YzIiwicmVmcmVzaF90b2tlbiI6IlV5RnA3eDFaVWp4bk9jRVEiLCJ0b2tlbl90eXBlIjoibWFjIiwiZXhwaXJlX2luIjoyNTkyMDAwfQ%3D%3D&limit=20&category=all&recomment=1"));
        CockroachApplication.run(JueJinApplication.class, queue);
    }
}
