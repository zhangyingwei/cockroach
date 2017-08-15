package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.NameUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuter implements Runnable {
    private TaskQueue queue;
    private HttpClient httpClient;
    private IStore store;
    private String id;
    private boolean autoClose;
    private int sleep;

    public TaskExecuter(TaskQueue queue, HttpClient httpClient,IStore store,int sleep,boolean autoClose) {
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
                TaskResponse response = this.httpClient.proxy().doGet(task);
                this.store.store(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(id+" : 结束");
    }
}
