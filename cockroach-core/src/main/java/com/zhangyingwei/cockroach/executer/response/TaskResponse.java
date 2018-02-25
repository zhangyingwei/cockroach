package com.zhangyingwei.cockroach.executer.response;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.zhangyingwei.cockroach.exception.HttpException;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.common.utils.CockroachUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/10.
 * 请求返回结构
 */
public class TaskResponse implements ICockroachResponse {
    private Map<String, List<String>> headers;
    private Task task;
    private Document document;
    private JXDocument xdocument;
    private CockroachQueue queue;
    private String content;
    private byte[] contentBytes;
    private String charset;
    private boolean failed = false;

    public TaskResponse(byte[] contentBytes, Map<String, List<String>> headers, int code, Task task) throws IOException, HttpException {
        this.contentBytes = contentBytes;
        this.task = task;
        this.headers = headers;
        if (!CockroachUtils.validHttpCode(code)) {
            throw new HttpException(this.getContent(),code);
        }
    }

    public TaskResponse() {}

    @Override
    public String getContent() throws IOException {
        if (null == this.content) {
            if (null != this.charset) {
                this.content = new String(this.contentBytes, this.charset);
            } else {
                this.content = new String(this.contentBytes);
            }
        }
        return this.content;
    }

    public byte[] getContentBytes() throws IOException {
        return this.contentBytes;
    }

    public Document getDocument() throws IOException {
        this.document = this.parseDocument();
        return document;
    }

    private Document parseDocument() throws IOException {
        if(this.document == null){
            this.document = Jsoup.parse(Optional.ofNullable(this.getContent()).orElse(""));
        }
        return this.document;
    }

    private JXDocument parseJXDocument() throws IOException {
        if (this.xdocument == null) {
            Document doc = this.parseDocument();
            this.xdocument = new JXDocument(doc);
        }
        return this.xdocument;
    }

    @Override
    public Task getTask() {
        return task;
    }

    public TaskResponse charset(String charset) {
        this.charset = charset;
        return this;
    }

    public TaskResponse setTask(Task task) {
        this.task = task;
        return this;
    }

    public Elements select(String cssSelect) throws IOException {
        return this.parseDocument().select(cssSelect);
    }

    public Elements xpath(String xpath) throws IOException, XpathSyntaxErrorException {
        List<Element> elements = this.parseJXDocument().sel(xpath).stream().map(obj -> {
            return (Element) obj;
        }).collect(Collectors.toList());
        return new Elements(Optional.of(elements).orElse(new ArrayList<Element>()));
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

    public void setQueue(CockroachQueue queue) {
        this.queue = queue;
    }

    @Override
    public CockroachQueue getQueue() {
        return queue;
    }

    @Override
    public List<String> header(String key) {
        if (this.headers.containsKey(key)) {
            return this.headers.get(key);
        }
        return null;
    }

    public boolean isFalied() {
        return this.failed;
    }

    public TaskResponse falied(String message) {
        this.failed = true;
        this.content = message;
        return this;
    }
}