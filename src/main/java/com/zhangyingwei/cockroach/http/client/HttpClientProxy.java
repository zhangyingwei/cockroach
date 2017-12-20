package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.NoCookieGenerator;
import com.zhangyingwei.cockroach.common.generators.NoHeaderGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.ProxyTuple;
import com.zhangyingwei.cockroach.http.exception.Http30XException;
import com.zhangyingwei.cockroach.http.exception.Http40XException;
import com.zhangyingwei.cockroach.http.exception.Http50XException;
import com.zhangyingwei.cockroach.http.exception.HttpException;
import org.apache.log4j.Logger;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/8/16
 * @time: 下午8:52
 * @desc:
 */
public class HttpClientProxy implements HttpClient {
    private Logger logger = Logger.getLogger(HttpClientProxy.class);
    private HttpClient client;
    private HttpProxy proxy;
    private StringGenerator cookieGenerator;
    private MapGenerator headerGenerator;

    public HttpClientProxy(HttpClient client) {
        this.client = client;
    }

    @Override
    public HttpClient setProxy(HttpProxy proxy) {
        this.proxy = proxy;
        try {
            this.client.setProxy(proxy);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public TaskResponse doGet(Task task) {
        this.makeGenerators(task);
        String message = "";
        try {
            return this.client.doGet(task);
        } catch (Exception e) {
            if (e instanceof HttpException) {
                if(e instanceof Http40XException){
                    message = e.getMessage();
                }else if(e instanceof Http50XException){
                    message = e.getMessage();
                }else if(e instanceof Http30XException){
                    message = "resources redirect:" + e.getMessage();
                }
            } else {
                if (this.proxy != null) {
                    this.proxy.disable(this.getCurrentProxyTuple());
                }
                message = e.getMessage();
            }
            logger.error(task + " - " + message);
        }
        return TaskResponse.empty().setTask(task).setMessage(message);
    }

    /**
     * 如果配置了生成器，则在请求之前调用生成器
     * @param task
     */
    private void makeGenerators(Task task) {
        if (this.cookieGenerator != null) {
            if (!(this.cookieGenerator instanceof NoCookieGenerator)) {
                this.setCookie(this.cookieGenerator.get(task));
            }
        }
        if (this.headerGenerator != null) {
            if (!(this.headerGenerator instanceof NoHeaderGenerator)) {
                Map headers = this.headerGenerator.get(task);
                this.setHttpHeader(headers);
            }
        }
    }

    @Override
    public HttpClient proxy() {
        if(this.proxy != null && !this.proxy.isEmpty()){
            try {
                this.client.proxy();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return this;
    }

    @Override
    public TaskResponse doPost(Task task) {
        try {
            return this.client.doPost(task);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return TaskResponse.empty().setTask(task);
        }
    }

    @Override
    public HttpClient setCookie(String cookie) {
        try {
            this.client.setCookie(cookie);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public HttpClient setHttpHeader(Map<String, String> httpHeader) {
        try {
            this.client.setHttpHeader(httpHeader);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() {
        try {
            return this.client.getCurrentProxyTuple();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public HttpClient showProgress(Boolean show) {
        this.client.showProgress(show);
        return this;
    }

    public HttpClientProxy setCookieGenerator(StringGenerator cookieGenerator){
        this.cookieGenerator = cookieGenerator;
        return this;
    }

    public HttpClientProxy setHeaderGenerator(MapGenerator headerGenerator) {
        this.headerGenerator = headerGenerator;
        return this;
    }
}
