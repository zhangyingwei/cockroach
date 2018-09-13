package com.zhangyingwei.cockroach.http.handler;

import com.zhangyingwei.cockroach.executer.response.TaskErrorResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.client.HttpClientProxy;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class DefaultTaskErrorHandler implements ITaskErrorHandler {
    private Logger logger = Logger.getLogger(DefaultTaskErrorHandler.class);

    @Override
    public void error(TaskErrorResponse response) {
        try {
            response.getQueue().falied(response.getTask());
            logger.info("task error: "+ response.getContent());
            this.validProxy(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validProxy(TaskErrorResponse response) throws IOException {
        String message = response.getContent().string();
        HttpClientProxy clientProxy = (HttpClientProxy) response.response().getHttpClient();
        HttpProxy proxy = clientProxy.getProxy();
        if (message != null &&
                (
                        message.toLowerCase().contains("timeout")
                                || message.toLowerCase().contains("time out")
                                || message.toLowerCase().contains("connect: 403")
                )) {
            if (proxy != null && !proxy.isEmpty()) {
                proxy.disable(clientProxy.getCurrentProxyTuple());
            }
        }
    }
}
