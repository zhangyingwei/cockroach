package com.zhangyingwei.cockroach.executer.response.filter;

import com.zhangyingwei.cockroach.common.cache.CockroachCache;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhangyw
 * @date: 2018/1/24
 * @time: 下午3:24
 * @desc:
 */
public class TaskResponseFilterBox implements ITaskResponseFilter {
    private Set<ITaskResponseFilter> responseFilters;
    private Logger logger = Logger.getLogger(TaskResponseFilterBox.class);
    public TaskResponseFilterBox() {
        this.responseFilters = new HashSet<ITaskResponseFilter>();
    }

    @Override
    public synchronized boolean accept(TaskResponse response) {
        for (ITaskResponseFilter responseFilter : responseFilters) {
            if (!responseFilter.accept(response)) {
                logger.info(Thread.currentThread().getName() + " response of " + response.getTask() + " is not accepted by " + responseFilter.getClass());
                return false;
            }
        }
        return true;
    }

    public void addFilter(ITaskResponseFilter responseFilter) {
        this.responseFilters.add(responseFilter);
    }
}
