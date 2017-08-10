package com.zhangyingwei.cockroach.http;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import net.sf.json.JSONObject;
import okhttp3.*;

import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class COkHttpClient implements HttpClient {
    private OkHttpClient okHttpClient;

    public COkHttpClient() {
        this.okHttpClient = new OkHttpClient();
    }

    @Override
    public TaskResponse doGet(Task task) throws Exception {
        String params = String.join("&", task.getParams().entrySet().stream()
                .map(entity -> entity.getKey() + "=" + entity.getValue())
                .collect(Collectors.toList()));
        Request request = new Request.Builder()
                .url(String.format("%s?%S",task.getUrl(),""))
                .headers(Headers.of(HttpParams.headers()))
                .get()
                .build();
        System.out.println(task);
        Response response = this.okHttpClient.newCall(request).execute();
        return TaskResponse.of(response.body().string(),task);
    }

    @Override
    public TaskResponse doPost(Task task) throws Exception {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                JSONObject.fromObject(task.getParams()).toString()
        );
        Request request = new Request.Builder()
                .url(task.getUrl())
                .headers(Headers.of(HttpParams.headers()))
                .post(requestBody)
                .build();
        Response response = this.okHttpClient.newCall(request).execute();
        return TaskResponse.of(response.message(),task);
    }
}
