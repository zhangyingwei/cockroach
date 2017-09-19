package com.zhangyingwei.cockroach.http.client.okhttp;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpParams;
import com.zhangyingwei.cockroach.http.client.AbsHttpClient;
import com.zhangyingwei.cockroach.http.client.HttpClient;
import net.sf.json.JSONObject;
import okhttp3.*;
import org.apache.log4j.Logger;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class COkHttpClient extends AbsHttpClient {
    private Logger logger = Logger.getLogger(COkHttpClient.class);
    private OkHttpClient okHttpClient;

    public COkHttpClient() {
        this.okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieManager()).build();
        this.httpHeader.put("cockroach", "hello-cockroach");
    }

    @Override
    public TaskResponse doGet(Task task) throws Exception {
        String params = String.join("&", task.getParams().entrySet().stream()
                .map(entity -> entity.getKey() + "=" + entity.getValue())
                .collect(Collectors.toList()));
        Request request = new Request.Builder()
                .url(String.format("%s",task.getUrl(),""))
                .headers(Headers.of(HttpParams.headers(this.httpHeader)))
                .get()
                .build();
        Response response = this.okHttpClient.newCall(request).execute();
        return TaskResponse.of(response, task);
    }

    @Override
    public HttpClient proxy() {
        if(this.proxy != null && !this.proxy.isEmpty()){
            this.proxyTuple = this.proxy.randomProxy();
            this.okHttpClient = this.okHttpClient.newBuilder()
                    .cookieJar(new CookieManager(this.cookie))
                    .proxy(
                            new Proxy(
                                    Proxy.Type.HTTP,
                                    new InetSocketAddress(this.proxyTuple.ip(),this.proxyTuple.port())
                            )
                    ).build();
            logger.info("代理:"+this.proxyTuple);
        }
        return this;
    }

    @Override
    public TaskResponse doPost(Task task) throws Exception {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                JSONObject.fromObject(task.getParams()).toString()
        );
        Request request = new Request.Builder()
                .url(task.getUrl())
                .headers(Headers.of(HttpParams.headers(this.httpHeader)))
                .post(requestBody)
                .build();
        Response response = this.okHttpClient.newCall(request).execute();
        return TaskResponse.of(response,task);
    }

    @Override
    public HttpClient setCookie(String cookie) {
        this.cookie = cookie;
        this.okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieManager(this.cookie)).build();
        return this;
    }
}
