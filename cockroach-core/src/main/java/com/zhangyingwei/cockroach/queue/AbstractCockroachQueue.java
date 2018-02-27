package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.filter.IQueueTaskFilter;
import com.zhangyingwei.cockroach.queue.filter.TaskFilterBox;

import java.util.List;

/**
 * Created by zhangyw on 2017/9/13.
 * 队列接口
 */
public abstract class AbstractCockroachQueue implements CockroachQueue {
    protected TaskFilterBox filterBox;

    public AbstractCockroachQueue() {
        this.filterBox = new TaskFilterBox();
    }

    @Override
    public CockroachQueue filter(IQueueTaskFilter filter) throws Exception {
        this.filterBox.add(filter);
        return this;
    }
}
