package com.zhangyingwei.cockroach.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class HttpProxy {
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

    public ProxyTuple randomProxy(){
        synchronized (this.proxys){
            if (this.proxys.size() == 0) {
                System.out.println("INFO: 代理全部失效");
            }
            Map.Entry<String,Integer> entity = this.proxys.entrySet().stream().collect(Collectors.toList()).get(random.nextInt(proxys.size()));
            return new ProxyTuple(entity.getKey(), entity.getValue());
        }
    }

    public void disable(ProxyTuple proxy){
        synchronized (this.proxys) {
            System.out.println("INFO: disable-" + proxy);
            this.proxys.remove(proxy.ip());
        }
    }
}
