package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.Task;
import org.apache.commons.lang.StringUtils;

/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:37
 * @desc:
 */
public class TestQueueTaskFilter implements IQueueTaskFilter {
    @Override
    public boolean accept(Task task) {
        return StringUtils.isNotBlank(task.getUrl()) && task.getUrl().contains("baidu");
    }
}
