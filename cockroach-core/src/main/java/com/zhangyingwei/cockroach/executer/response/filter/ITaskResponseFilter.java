package com.zhangyingwei.cockroach.executer.response.filter;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;

/**
 * @author: zhangyw
 * @date: 2018/1/24
 * @time: 下午3:12
 * @desc:
 */
public interface ITaskResponseFilter {
    boolean accept(TaskResponse response);
}
