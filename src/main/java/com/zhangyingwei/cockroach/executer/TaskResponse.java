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
public class TaskResponse implements ICockroachResponse {
    private Task task;
    private Document document;
    private List<String> selects;
    private CockroachQueue queue;
    private Response response;
    private String message;
    private String content;
    private byte[] contentBytes;

    @Override
    public String getContent() throws IOException {
        return this.content;
    }

    public byte[] getContentBytes() throws IOException {
        this.contentBytes = this.content.getBytes();
        return this.contentBytes;
    }

    public Document getDocument() throws IOException {
        this.document = this.parseDocument();
        return document;
    }

    private Document parseDocument() throws IOException {
        if(this.document == null){
            this.document = Jsoup.parse(Optional.ofNullable(this.content).orElse(""));
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

    @Override
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

    public Boolean isEmpty(){
        return this.response == null && this.selects == null;
    }

    public static TaskResponse of(Response response, Task task) throws HttpException, IOException {
        int code = response.code();
        String message = response.message();
        String location = response.header("Location");
        switch (code) {
            case 200:
                return new TaskResponse().setResponse(response).setSelects(task.getSelects()).setTask(task);
            case 300:
                throw new Http300Exception(location);
            case 301:
                throw new Http301Exception(location);
            case 302:
                throw new Http302Exception(location);
            case 400:
                throw new Http400Exception(message);
            case 401:
                throw new Http401Exception(message);
            case 403:
                throw new Http403Exception(message);
            case 404:
                throw new Http404Exception(message);
            case 405:
                throw new Http405Exception(message);
            case 406:
                throw new Http406Exception(message);
            case 407:
                throw new Http407Exception(message);
            case 408:
                throw new Http408Exception(message);
            case 500:
                throw new Http500Exception(message);
            case 501:
                throw new Http501Exception(message);
            case 502:
                throw new Http502Exception(message);
            case 503:
                throw new Http503Exception(message);
            case 504:
                throw new Http504Exception(message);
            case 505:
                throw new Http505Exception(message);
            default:
                throw new HttpException(code + "-" + message);
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

    @Override
    public boolean isGroup(String group){
        return task.getGroup().equals(group);
    }

    @Override
    public boolean isGroupStartWith(String groupPrefix) {
        return task.getGroup().startsWith(groupPrefix);
    }

    @Override
    public boolean isGroupEndWith(String end) {
        return task.getGroup().endsWith(end);
    }

    @Override
    public boolean isGroupContains(String str) {
        return task.getGroup().contains(str);
    }

    public String getGroup(){
        return this.task.getGroup();
    }

    public void setQueue(CockroachQueue queue) {
        this.queue = queue;
    }

    @Override
    public CockroachQueue getQueue() {
        return queue;
    }

    public TaskResponse setResponse(Response resoonse) throws IOException {
        this.response = resoonse;
        this.content = response.body().string();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TaskResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response getResponse() {
        return response;
    }
}