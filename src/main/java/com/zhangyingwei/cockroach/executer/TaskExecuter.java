package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.HttpClient;
import com.zhangyingwei.cockroach.store.IStore;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class TaskExecuter implements Runnable {

    private TaskQueue queue;
    private HttpClient httpClient;
    private IStore store;

    public TaskExecuter(TaskQueue queue, HttpClient httpClient,IStore store) {
        this.queue = queue;
        this.httpClient = httpClient;
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = this.queue.pull();
                TaskResponse response = this.httpClient.doGet(task);
                this.store.store(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
