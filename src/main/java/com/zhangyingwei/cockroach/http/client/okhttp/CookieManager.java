package com.zhangyingwei.cockroach.http.client.okhttp;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class CookieManager implements CookieJar {

    private Map<String, List<Cookie>> cookies = new HashMap<String,List<Cookie>>();

    private String cookie;

    public CookieManager() {
    }

    public CookieManager(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        cookies.put(httpUrl.host(), list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> list = Arrays.stream(this.cookie.split(";"))
                .map(line -> line.split("="))
                .filter(item -> item.length > 1)
                .map(item -> new Cookie.Builder().name(item[0].trim()).value(item[1]).domain(httpUrl.host()).build()).collect(Collectors.toList());
        return Optional.ofNullable(cookies.get(httpUrl.host())).orElse(list);
    }
}
