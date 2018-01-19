package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.Task;

/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:22
 * @desc:
 */
public interface IQueueTaskFilter {
    boolean accept(Task task);
}
