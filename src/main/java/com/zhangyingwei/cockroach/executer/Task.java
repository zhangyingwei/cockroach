package com.zhangyingwei.cockroach.executer;


import com.zhangyingwei.cockroach.utils.NameUtils;

import java.util.*;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class Task{
    private String id = NameUtils.name(Task.class);
    private String group = "default";
    private String url;
    private Map<String, Object> params;
    private List<String> selects;

    public Task(String url, Map<String, Object> params) {
        this.url = url;
        this.params = params;
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

    public Task(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        if(this.params ==null){
            this.params = new HashMap<String,Object>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<String> getSelects() {
        return selects;
    }

    public Task addSelect(String cssSelect){
        this.selects = Optional.ofNullable(this.selects).orElse(new ArrayList<String>());
        this.selects.add(cssSelect);
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "url='" + url + '\'' +
                '}';
    }
}
