package com.zhangyingwei.cockroach.queue.filter;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.common.interfaces.IBox;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhangyw
 * @date: 2018/1/24
 * @time: 下午2:58
 * @desc:
 */
public class TaskFilterBox implements IQueueTaskFilter,IBox<IQueueTaskFilter> {
    private static Logger logger = Logger.getLogger(TaskFilterBox.class);
    private Set<IQueueTaskFilter> filters;

    public TaskFilterBox() {
        this.filters = new HashSet<IQueueTaskFilter>();
        this.filtersInit();
    }

    /**
     * 初始化过滤器
     */
    private void filtersInit() {
        this.filters.add(new DefaultQueueTaskFilter());
        this.filters.add(new DefaultRepeatFilter());
    }

    @Override
    public synchronized boolean accept(Task task) {
        for (IQueueTaskFilter filter : filters) {
            if (!filter.accept(task)) {
                logger.info(Thread.currentThread().getName() + " " + task + " is not accepted by " + filter.getClass());
                return false;
            }
        }
        return true;
    }

    @Override
    public IQueueTaskFilter add(IQueueTaskFilter filter) {
        this.filters.add(filter);
        return this;
    }
}
