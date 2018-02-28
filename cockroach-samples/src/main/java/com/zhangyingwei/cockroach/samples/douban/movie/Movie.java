package com.zhangyingwei.cockroach.samples.douban.movie;

import java.util.List;

/**
 * Created by zhangyw on 2018/2/28.
 */
public class Movie {
    private String title;
    private String rate;
    private String url;
    private String id;
    private List<String> directors; //导演
    private List<String> casts; //主演

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

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getCasts() {
        return casts;
    }

    public void setCasts(List<String> casts) {
        this.casts = casts;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", rate='" + rate + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", directors=" + directors +
                ", casts=" + casts +
                '}';
    }
}
