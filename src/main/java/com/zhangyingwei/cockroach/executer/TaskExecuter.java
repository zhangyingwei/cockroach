package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.NameUtils;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuter implements Runnable {
    private TaskQueue queue;
    private HttpClient httpClient;
    private IStore store;
    private String id;

    public TaskExecuter(TaskQueue queue, HttpClient httpClient,IStore store) {
        this.queue = queue;
        this.httpClient = httpClient;
        this.store = store;
        this.id = NameUtils.name(TaskExecuter.class);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = this.queue.pull();
                TaskResponse response = this.httpClient.proxy().doGet(task);
                this.store.store(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
