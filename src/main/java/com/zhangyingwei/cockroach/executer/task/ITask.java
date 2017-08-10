package com.zhangyingwei.cockroach.executer.task;

import com.zhangyingwei.cockroach.executer.TaskContext;
import com.zhangyingwei.cockroach.executer.TaskResponse;

import java.util.Map;

/**
 * Created by zhangyw on 2017/8/10.
 * 网页抓取任务接口
 */
public interface ITask {
    boolean before(TaskContext context) throws Exception;
    ITask doGet(String url, Map<String,Object> params) throws Exception;
    boolean after(TaskContext context) throws Exception;
    TaskResponse execute() throws Exception;
}
