package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.ExecuterManager;
import com.zhangyingwei.cockroach.executer.listener.BootstrapExecutersListener;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContext {
    private Logger logger = Logger.getLogger(CockroachContext.class);
    private CockroachConfig config;
    private boolean started = false;
    private ExecuterManager executerManager;

    public CockroachContext(final CockroachConfig config) {
        this.config = config;
        this.executerManager = new ExecuterManager(this.config);
    }

    /**
     * 启动爬虫程序
     * 只能启动一次，启动之前先判断之前有没有启动过
     * @param queue
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void start(CockroachQueue queue) {
        if(!started){
            logger.info("starting...");
            config.print();
            try {
                this.executerManager.bindListener(BootstrapExecutersListener.class).bindListener(this.config.getExecutersListener()).start(queue);
            } catch (Exception e) {
                logger.info("start faild");
                logger.debug(e.getMessage());
                e.printStackTrace();
            }
            this.started = true;
            logger.info("start success");
        }else{
            logger.warn("the cockroach has already started");
        }
    }
}
