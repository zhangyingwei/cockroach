package com.zhangyingwei.cockroach.http.client.okhttp;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import sun.net.ProgressListener;

/**
 * Created by zhangyw on 2017/12/12.
 */
public class ProgressResponseBody extends ResponseBody {
    public static final int UPDATE = 0x01;
    public static final String TAG = ProgressResponseBody.class.getName();
    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    public ProgressResponseBody(ResponseBody body) {
        responseBody = body;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        bufferedSource = responseBody.source();
        return bufferedSource;
    }
}