package com.zhangyingwei.cockroach.executer.listener;

import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2018/2/5.
 * 初始化一些系统功能
 */
public class BootstrapExecutersListener implements IExecutersListener {
    private Logger logger = Logger.getLogger(BootstrapExecutersListener.class);
    @Override
    public void onStart() {
        logger.info("BootstrapExecutersListener.onStart");
    }

    @Override
    public void onEnd() {
        logger.info("BootstrapExecutersListener.onEnd");
    }
}
