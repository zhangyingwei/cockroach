package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskErrorResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class DefaultTaskErrorHandler implements ITaskErrorHandler {
    private Logger logger = Logger.getLogger(DefaultTaskErrorHandler.class);

    @Override
    public void error(TaskErrorResponse response) {
        try {
            logger.info("task error: "+ response.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
