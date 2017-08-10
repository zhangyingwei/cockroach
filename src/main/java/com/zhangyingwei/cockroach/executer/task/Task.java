package com.zhangyingwei.cockroach.executer.task;

import com.zhangyingwei.cockroach.executer.TaskContext;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpClient;

import java.util.Map;
import java.util.Optional;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class Task implements ITask{
    private TaskContext context;
    private HttpClient httpClient;

    public Task(HttpClient httpClient) {
        this.context = new TaskContext();
        this.httpClient = httpClient;
    }

    @Override
    public boolean before(TaskContext context) throws Exception {
        return true;
    }

    @Override
    public ITask doGet(String url, Map<String, Object> params) throws Exception {
        this.context.setUrl(url);
        this.context.setParames(params);
        return this;
    }

    @Override
    public boolean after(TaskContext context) throws Exception {
        return true;
    }

    @Override
    public TaskResponse execute() throws Exception {
        boolean bef = this.before(this.context);
        Optional<TaskResponse> result = Optional.empty();
        if(bef){
            result = Optional.of(httpClient.doGet(this.context));
        }
        this.context.setResponse(result);
        boolean aft = this.after(this.context);
        if(!aft){
            result = Optional.empty();
        }
        return result.orElse(TaskResponse.empty());
    }
}
