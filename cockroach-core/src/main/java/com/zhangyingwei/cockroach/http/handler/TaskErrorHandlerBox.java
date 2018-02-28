package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.executer.response.TaskErrorResponse;
import com.zhangyingwei.cockroach.common.interfaces.IBox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/2/10
 * @time: 下午2:12
 * @desc:
 */
public class TaskErrorHandlerBox implements ITaskErrorHandler,IBox<ITaskErrorHandler> {
    private List<ITaskErrorHandler> errorHandlers;

    public TaskErrorHandlerBox() {
        this.errorHandlers = new ArrayList<ITaskErrorHandler>();
    }

    @Override
    public void error(TaskErrorResponse response) {
        errorHandlers.forEach(handler -> {
            handler.error(response);
        });
    }

    @Override
    public ITaskErrorHandler add(ITaskErrorHandler model) {
        this.errorHandlers.add(model);
        return this;
    }
}
