package com.zhangyingwei.cockroach.executer;


import java.util.*;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class Task{
    private String url;
    private Map<String, Object> params;
    private List<String> selects;

    public Task(String url, Map<String, Object> params) {
        this.url = url;
        this.params = params;
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
}
