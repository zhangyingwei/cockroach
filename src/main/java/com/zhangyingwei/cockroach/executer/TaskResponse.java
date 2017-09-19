package com.zhangyingwei.cockroach.executer;

import com.zhangyingwei.cockroach.http.exception.*;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhangyw on 2017/8/10.
 * 请求返回结构
 */
public class TaskResponse {
    private Task task;
    private Document document;
    private List<String> selects;
    private CockroachQueue queue;
    private Response response;

    public String getContent() throws IOException {
        return response.body().string();
    }

    public byte[] getContentBytes() throws IOException {
        return response.body().bytes();
    }

    public Document getDocument() throws IOException {
        this.document = this.parseDocument();
        return document;
    }

    private Document parseDocument() throws IOException {
        if(this.document == null){
            this.document = Jsoup.parse(Optional.ofNullable(this.response.body().string()).orElse(""));
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

    public static TaskResponse of(Response response, Task task) throws HttpException, IOException {
        int code = response.code();
        String location = response.header("Location");
        switch (code) {
            case 200:
                return new TaskResponse().setResoonse(response).setSelects(task.getSelects()).setTask(task);
            case 300:
                throw new Http300Exception(location);
            case 301:
                throw new Http301Exception(location);
            case 302:
                throw new Http302Exception(location);
            case 400:
                throw new Http400Exception();
            case 401:
                throw new Http401Exception();
            case 403:
                throw new Http403Exception();
            case 404:
                throw new Http404Exception();
            case 405:
                throw new Http405Exception();
            case 406:
                throw new Http406Exception();
            case 407:
                throw new Http407Exception();
            case 408:
                throw new Http408Exception();
            case 500:
                throw new Http500Exception();
            case 501:
                throw new Http501Exception();
            case 502:
                throw new Http502Exception();
            case 503:
                throw new Http503Exception();
            case 504:
                throw new Http504Exception();
            case 505:
                throw new Http505Exception();
            default:
                throw new HttpException(code + "");
        }
    }

    public Map<String,Elements> select() throws IOException {
        Document doc = this.parseDocument();
        Map<String, Elements> results = new HashMap<String,Elements>();
        Optional.ofNullable(this.selects).orElse(new ArrayList<String>()).forEach(cssquery ->{
            results.put(cssquery, doc.select(cssquery));
        });
        return results;
    }

    public Elements select(String cssSelect) throws IOException {
        return this.parseDocument().select(cssSelect);
    }

    public boolean isGroup(String group){
        return task.getGroup().equals(group);
    }

    public String getGroup(){
        return this.task.getGroup();
    }

    public void setQueue(CockroachQueue queue) {
        this.queue = queue;
    }

    public CockroachQueue getQueue() {
        return queue;
    }

    public TaskResponse setResoonse(Response resoonse) {
        this.response = resoonse;
        return this;
    }

    public Response getResoonse() {
        return response;
    }
}