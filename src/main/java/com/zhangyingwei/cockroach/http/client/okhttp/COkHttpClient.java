package com.zhangyingwei.cockroach.http.client.okhttp;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpParams;
import com.zhangyingwei.cockroach.http.ProxyTuple;
import com.zhangyingwei.cockroach.http.client.AbstractHttpClient;
import com.zhangyingwei.cockroach.http.client.IHttpClient;
import net.sf.json.JSONObject;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.DecimalFormat;


/**
 * Created by zhangyw on 2017/8/10.
 */
public class COkHttpClient extends AbstractHttpClient {
    private Logger logger = Logger.getLogger(COkHttpClient.class);
    private OkHttpClient.Builder clientBuilder;

    public COkHttpClient() {
        this.clientBuilder = new OkHttpClient.Builder().cookieJar(new CookieManager());
        this.httpHeader.put("cockroach", "hello-cockroach");
    }

    @Override
    public TaskResponse doGet(Task task) throws Exception {
        Request request = new Request.Builder()
                .url(task.getUrl())
                .headers(Headers.of(HttpParams.headers(this.httpHeader)))
                .get()
                .build();
        Response response = this.clientBuilder.build().newCall(request).execute();
        TaskResponse taskResponse = new TaskResponse(response.body().bytes(), response.headers().toMultimap(), response.code(), task);
        response.close();
        return taskResponse;
    }

    @Override
    public IHttpClient proxy(ProxyTuple proxy) {
        super.currentProxy = proxy;
        if (proxy != null) {
            this.clientBuilder = this.clientBuilder
                    .cookieJar(new CookieManager(this.cookie))
                    .proxy(
                            new Proxy(
                                    Proxy.Type.HTTP,
                                    new InetSocketAddress(super.currentProxy.ip(), super.currentProxy.port())
                            )
                    );
            logger.info("代理:" + super.currentProxy);
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
        Response response = this.clientBuilder.build().newCall(request).execute();
        TaskResponse taskResponse = new TaskResponse(response.body().bytes(), response.headers().toMultimap(), response.code(), task);
        response.close();
        return taskResponse;
    }

    @Override
    public IHttpClient setCookie(String cookie) {
        this.cookie = cookie;
        this.clientBuilder = this.clientBuilder.cookieJar(new CookieManager(this.cookie));
        return this;
    }
}
