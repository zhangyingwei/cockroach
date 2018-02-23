package com.zhangyingwei.cockroach.http.client;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.NoCookieGenerator;
import com.zhangyingwei.cockroach.common.generators.NoHeaderGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.http.HttpProxy;
import com.zhangyingwei.cockroach.http.ProxyTuple;
import org.apache.log4j.Logger;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/8/16
 * @time: 下午8:52
 * @desc:
 */
public class HttpClientProxy implements IHttpClient {
    private Logger logger = Logger.getLogger(HttpClientProxy.class);
    private IHttpClient client;
    private StringGenerator cookieGenerator;
    private MapGenerator headerGenerator;
    private HttpProxy proxy;

    public HttpClientProxy(IHttpClient client) {
        this.client = client;
    }

    @Override
    public TaskResponse doGet(Task task) {
        this.makeGenerators(task);
        String message = "";
        try {
            return this.client.proxy(this.randomProxy()).doGet(task);
        } catch (Exception e) {
            message = e.getMessage();
            if (message != null &&
                    (
                            message.toLowerCase().contains("timeout")
                                    || message.toLowerCase().contains("time out")
                                    || message.toLowerCase().contains("connect: 403")
                    )) {
                if (this.proxy != null && !this.proxy.isEmpty()) {
                    this.proxy.disable(this.getCurrentProxyTuple());
                }
            }
            logger.error(task + " - " + message);
        }
        return new TaskResponse().setTask(task).falied(message);
    }

    public HttpClientProxy setProxy(HttpProxy proxy) {
        this.proxy = proxy;
        return this;
    }

    /**
     * 这个方法暂时用不到，没用
     * @param proxy
     * @return
     * @throws Exception
     */
    @Override
    public HttpClientProxy proxy(ProxyTuple proxy) throws Exception {
        this.client.proxy(proxy);
        return this;
    }

    /**
     * 随机一个代理
     * @return
     */
    private ProxyTuple randomProxy() {
        if(this.proxy != null && !this.proxy.isEmpty()) {
            return this.proxy.randomProxy();
        }
        return null;
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
    public TaskResponse doPost(Task task) {
        try {
            return this.client.doPost(task);
        } catch (Exception e) {
            e.printStackTrace();
            return new TaskResponse().setTask(task).falied(e.getMessage());
        }
    }

    @Override
    public HttpClientProxy setCookie(String cookie) {
        try {
            this.client.setCookie(cookie);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public HttpClientProxy setHttpHeader(Map<String, String> httpHeader) {
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

    public HttpClientProxy setCookieGenerator(StringGenerator cookieGenerator){
        this.cookieGenerator = cookieGenerator;
        return this;
    }

    public HttpClientProxy setHeaderGenerator(MapGenerator headerGenerator) {
        this.headerGenerator = headerGenerator;
        return this;
    }
}
