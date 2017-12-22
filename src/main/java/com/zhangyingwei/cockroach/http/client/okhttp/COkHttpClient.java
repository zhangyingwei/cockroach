package com.zhangyingwei.cockroach.http.client.okhttp;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpParams;
import com.zhangyingwei.cockroach.http.client.AbstractHttpClient;
import com.zhangyingwei.cockroach.http.client.HttpClient;
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
        return TaskResponse.of(response, task);
    }

    @Override
    public HttpClient proxy() {
        if(this.proxy != null && !this.proxy.isEmpty()){
            this.proxyTuple = this.proxy.randomProxy();
            this.clientBuilder = this.clientBuilder
                    .cookieJar(new CookieManager(this.cookie))
                    .proxy(
                            new Proxy(
                                    Proxy.Type.HTTP,
                                    new InetSocketAddress(this.proxyTuple.ip(),this.proxyTuple.port())
                            )
                    );
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
        Response response = this.clientBuilder.build().newCall(request).execute();
        return TaskResponse.of(response,task);
    }

    @Override
    public HttpClient setCookie(String cookie) {
        this.cookie = cookie;
        this.clientBuilder = this.clientBuilder.cookieJar(new CookieManager(this.cookie));
        return this;
    }

    /**
     * 显示进度
     */
    @Override
    public HttpClient showProgress(Boolean show){
        if (!show) {
            return null;
        }
        this.clientBuilder.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ResponseBody() {
                    private BufferedSource bufferedSource;
                    @Override
                    public MediaType contentType() {
                        return response.body().contentType();
                    }
                    @Override
                    public long contentLength() {
                        return response.body().contentLength();
                    }
                    @Override
                    public BufferedSource source() {
                        if (this.bufferedSource == null) {
                            this.bufferedSource = Okio.buffer(new ForwardingSource(response.body().source()) {
                                Double count = 0.0;
                                Double all = 0.0 + response.body().contentLength();
                                DecimalFormat format = new DecimalFormat("0.0");
                                @Override
                                public long read(Buffer sink, long byteCount) throws IOException {
                                    Long read = super.read(sink, byteCount);
                                    count += read;
                                    logger.info(Thread.currentThread().getName() + " dowload:" + format.format((count/all)*100) +"%");
                                    return read;
                                }
                            });
                        }
                        return this.bufferedSource;
                    }
                }).build();
            }
        });
        return this;
    }
}
