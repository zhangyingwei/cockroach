package com.zhangyingwei.cockroach.queue.filter;

import com.zhangyingwei.cockroach.executer.task.Task;
import org.apache.commons.lang.StringUtils;

/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:27
 * @desc:
 */
public class DefaultQueueTaskFilter implements IQueueTaskFilter {
    @Override
    public boolean accept(Task task) {
        return task != null && StringUtils.isNotBlank(task.getUrl());
    }
}
