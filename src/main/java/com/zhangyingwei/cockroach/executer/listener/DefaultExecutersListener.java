package com.zhangyingwei.cockroach.executer.listener;

import org.apache.log4j.Logger;

/**
 * @author: zhangyw
 * @date: 2018/2/1
 * @time: 下午9:42
 * @desc:
 */
public class DefaultExecutersListener implements IExecutersListener {
    private Logger logger = Logger.getLogger(DefaultExecutersListener.class);
    @Override
    public void onStart() {
        logger.info("executers start...");
    }

    @Override
    public void onEnd() {
        logger.info("executers end...");
    }
}
