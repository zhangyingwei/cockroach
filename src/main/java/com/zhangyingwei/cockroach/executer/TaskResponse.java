package com.zhangyingwei.cockroach.executer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by zhangyw on 2017/8/10.
 * 请求返回结构
 */
public class TaskResponse {
    private Task task;
    private String content;
    private Document document;
    private List<String> selects;

    public String getContent() {
        return content;
    }

    public TaskResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Document getDocument() {
        this.document = this.parseDocument();
        return document;
    }

    private Document parseDocument() {
        if(this.document == null){
            this.document = Jsoup.parse(Optional.of(this.content).orElse(""));
        }
        return this.document;
    }

    public List<String> getSelects() {
        return selects;
    }

    public TaskResponse setSelects(List<String> selects) {
        this.selects = selects;
        return this;
    }

    public Task getTask() {
        return task;
    }

    public TaskResponse setTask(Task task) {
        this.task = task;
        return this;
    }

    public static TaskResponse empty() {
        return new TaskResponse();
    }

    public static TaskResponse of(String content, Task task){
        return new TaskResponse().setContent(content).setSelects(task.getSelects()).setTask(task);
    }

    public Map<String,Elements> select(){
        Document doc = this.parseDocument();
        Map<String, Elements> results = new HashMap<String,Elements>();
        Optional.ofNullable(this.selects).orElse(new ArrayList<String>()).forEach(cssquery ->{
            results.put(cssquery, doc.select(cssquery));
        });
        return results;
    }

    public Elements select(String cssSelect) {
        return this.parseDocument().select(cssSelect);
    }
}