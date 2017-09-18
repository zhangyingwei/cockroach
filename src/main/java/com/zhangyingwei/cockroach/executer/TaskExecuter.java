package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.NameUtils;
import org.apache.log4j.Logger;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangyw on 2017/8/10.
 * 任务执行器，主要工作是从队列中取出任务然后执行任务
 */
public class TaskExecuter implements Runnable {
    private Logger logger = Logger.getLogger(TaskExecuter.class);
    private CockroachQueue queue;
    private HttpClient httpClient;
    private IStore store;
    private String id;
    private boolean autoClose;
    private int sleep;

    public TaskExecuter(CockroachQueue queue, HttpClient httpClient,IStore store,int sleep,boolean autoClose) {
        this.queue = queue;
        this.httpClient = httpClient;
        this.store = store;
        this.id = NameUtils.name(TaskExecuter.class);
        this.autoClose = autoClose;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        boolean flag = true;
        loop:while (flag) {
            try {
                Task task = null;
                if(autoClose){
                    task = this.queue.poll();
                    if(task == null){
                        flag = false;
                        break loop;
                    }
                }else{
                    task = this.queue.take();
                }
                TimeUnit.MILLISECONDS.sleep(sleep);
                logger.info(this.getId()+" GET - "+task);
                TaskResponse response = this.httpClient.proxy().doGet(task);
                response.setQueue(this.queue);
                this.store.store(response);
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        logger.info(id+" : 结束");
    }

    public String getId() {
        return id;
    }
}
