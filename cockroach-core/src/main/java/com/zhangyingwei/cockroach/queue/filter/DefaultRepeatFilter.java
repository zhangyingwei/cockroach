package com.zhangyingwei.cockroach.queue.filter;

import com.zhangyingwei.cockroach.executer.task.Task;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhangyw
 * @date: 2018/1/24
 * @time: 下午2:37
 * @desc:
 */
public class DefaultRepeatFilter implements IQueueTaskFilter {
    private Set<String> urls;
    private Logger logger = Logger.getLogger(DefaultRepeatFilter.class);

    public DefaultRepeatFilter() {
        this.urls = new HashSet<String>();
    }

    @Override
    public boolean accept(Task task) {
        if (urls.contains(task.getUrl())) {
            return false;
        } else {
            urls.add(task.getUrl());
        }
        return true;
    }
}
