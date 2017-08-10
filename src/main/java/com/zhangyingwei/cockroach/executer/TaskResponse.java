package com.zhangyingwei.cockroach.executer;

/**
 * Created by zhangyw on 2017/8/10.
 * 请求返回结构
 */
public class TaskResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static TaskResponse empty() {
        return new TaskResponse();
    }
}