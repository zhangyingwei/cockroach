package com.zhangyingwei.cockroach.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class HttpParams {

    private static Random random = new Random();

    public static Map<String, String> headers(Map<String, String> httpHeader) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", randouAgent());
        if(httpHeader != null){
            headers.putAll(httpHeader);
        }
        return headers;
    }

    private static String randouAgent() {
        String[] agents = new String[]{
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36"
        };
        return agents[random.nextInt(agents.length)];
    }
}
