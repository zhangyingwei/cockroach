package com.zhangyingwei.cockroach.executer.response;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;

import java.io.IOException;
import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/12/18
 * @time: 下午7:54
 * @desc:
 */
public interface ICockroachResponse {
    String getContent() throws IOException;
    Task getTask();
    boolean isGroup(String group);
    boolean isGroupStartWith(String groupPrefix);
    boolean isGroupEndWith(String end);
    boolean isGroupContains(String str);
    CockroachQueue getQueue();
    List<String> header(String key);
}
