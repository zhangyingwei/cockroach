package com.zhangyingwei.cockroach.login;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.http.client.HttpClientProxy;
import com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


/**
 * @author: zhangyw
 * @date: 2017/12/6
 * @time: 下午8:48
 * @desc:
 */
public class LoginHandlerTest {
    @Test
    public void login() throws IOException {
        System.out.println("login");
        HttpClientProxy client = new HttpClientProxy(new COkHttpClient());
        TaskResponse res = client.doGet(new Task("http://baidu.com"));
        System.out.println(res.getContent());
    }
}