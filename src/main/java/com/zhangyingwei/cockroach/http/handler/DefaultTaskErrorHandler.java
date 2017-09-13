package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.executer.Task;
import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class DefaultTaskErrorHandler implements ITaskErrorHandler {
    private Logger logger = Logger.getLogger(DefaultTaskErrorHandler.class);
    @Override
    public void error(Task task,String message) {
        logger.info("task error: "+message);
    }
}
