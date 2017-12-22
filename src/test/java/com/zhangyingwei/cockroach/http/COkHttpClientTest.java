package com.zhangyingwei.cockroach.http;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class COkHttpClientTest {
    @Test
    public void doGet() throws Exception {
        COkHttpClient client = new COkHttpClient();
        TaskResponse resp = client.doGet(new Task("https://luolei.org").addSelect("a"));
        Assert.assertNotNull(resp.select());
    }
}