package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2017/12/8.
 */
public class DescribeStore implements IStore {
    private Logger logger = Logger.getLogger(DescribeStore.class);
    @Override
    public void store(TaskResponse response) throws Exception {
        logger.info("==================desc=================");
        logger.info(String.format("task id: %s",response.getTask().getId()));
        logger.info(String.format("thread name: %s",Thread.currentThread().getName()));
        logger.info(String.format("bytes: %d",response.getContent().bytes().length));
        logger.info("=======================================");
    }
}
