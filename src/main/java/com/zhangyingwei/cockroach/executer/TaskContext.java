package com.zhangyingwei.cockroach.executer;

import java.util.Map;
import java.util.Optional;

/**
 * Created by zhangyw on 2017/8/10.
 * task 上下文对象
 */
public class TaskContext {
    private String url;
    private Map<String, Object> parames;
    private Optional<TaskResponse> response;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParames() {
        return parames;
    }

    public void setParames(Map<String, Object> parames) {
        this.parames = parames;
    }

    public Optional<TaskResponse> getResponse() {
        return response;
    }

    public void setResponse(Optional<TaskResponse> response) {
        this.response = response;
    }
}
