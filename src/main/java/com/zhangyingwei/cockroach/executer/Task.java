package com.zhangyingwei.cockroach.executer;


import com.zhangyingwei.cockroach.config.Constants;
import com.zhangyingwei.cockroach.utils.NameUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/10.
 * 爬虫任务描述类
 */
public class Task {
    //每一个任务都会生成一个编号，编号是一个递增的连续序列
    private String id = NameUtils.name(Task.class);
    //每一个任务都会有一个分组，如果没有设置，默认为 default
    private String group = Constants.APP_TASK_GROUP_DEFAULT;
    private String url;
    private Map<String, Object> params;
    private List<String> selects;
    private Object extr;

    public Task(String url, Map<String, Object> params) {
        this.url = url;
        this.params = params;
    }

    public Task(String url) {
        this.url = url;
    }

    public Task(String url, String group) {
        this.url = url;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public Task setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getId() {
        return id;
    }

    public Task setId(String Id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        if (this.getParams().isEmpty()) {
            return url;
        } else {
            List<String> paramsList = this.getParams().entrySet().stream().map(entity -> String.format("%s=%s", entity.getKey(), entity.getValue())).collect(Collectors.toList());
            String param = StringUtils.join(paramsList.toArray(), "&");
            return String.format("%s?%s", url, param);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        if (this.params == null) {
            this.params = new HashMap<String, Object>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<String> getSelects() {
        return selects;
    }

    public Task addSelect(String cssSelect) {
        this.selects = Optional.ofNullable(this.selects).orElse(new ArrayList<String>());
        this.selects.add(cssSelect);
        return this;
    }

    public Object getExtr() {
        return extr;
    }

    public Task setExtr(Object extr) {
        this.extr = extr;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", group='" + group + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
