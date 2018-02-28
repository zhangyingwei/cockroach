package com.zhangyingwei.cockroach.samples.douban.movie;

/**
 * Created by zhangyw on 2018/2/28.
 */
public class Movie {
    private String title;
    private String rate;
    private String url;
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", rate=" + rate +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
