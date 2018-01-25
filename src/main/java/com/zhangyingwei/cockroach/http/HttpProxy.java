package com.zhangyingwei.cockroach.http;

import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class HttpProxy implements IHttpProxy{
    private Logger logger = Logger.getLogger(HttpProxy.class);
    private Map<String, Integer> proxys;
    private Random random = new Random();

    public HttpProxy(String proxys) {
        this.proxys = new HashMap<String, Integer>();
        Arrays.stream(proxys.split(",")).map(item -> item.split(":")).forEach(item -> {
            Integer ip = null;
            if(item.length > 1){
                ip = Integer.parseInt(item[1]);
            }
            this.proxys.put(item[0], ip);
        });
    }

    /**
     * 随机获取一个代理
     * @return
     */
    @Override
    public ProxyTuple randomProxy(){
        synchronized (this.proxys){
            Map.Entry<String,Integer> entity = this.proxys.entrySet().stream().collect(Collectors.toList()).get(random.nextInt(proxys.size()));
            return new ProxyTuple(entity.getKey(), entity.getValue());
        }
    }

    /**
     * 如果代理失效，从代理池中删除代理
     *
     * @param proxy
     */
    @Override
    public void disable(ProxyTuple proxy) {
        synchronized (this.proxys) {
            logger.info("disable-" + proxy);
            this.proxys.remove(proxy.ip());
            if (this.isEmpty()) {
                logger.info("代理全部失效");
            }
        }
    }

    @Override
    public boolean isEmpty(){
        return this.proxys.isEmpty();
    }
}
