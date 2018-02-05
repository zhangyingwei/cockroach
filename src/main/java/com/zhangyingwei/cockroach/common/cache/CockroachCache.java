package com.zhangyingwei.cockroach.common.cache;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangyw on 2018/2/5.
 */
public class CockroachCache implements ICache {

    private Logger logger = Logger.getLogger(CockroachCache.class);
    private List<CacheItem> cacheItems;
    private List<String> keys;
    private Thread thread;

    static class CockroachCacheHandler {
        static CockroachCache ins = new CockroachCache();
    }

    public static CockroachCache getInstance() {
        return CockroachCacheHandler.ins;
    }

    private CockroachCache() {
        this.init();
    }

    private void init() {
        this.keys = new ArrayList<String>();
        this.cacheItems = new ArrayList<CacheItem>();
    }

    @Override
    public synchronized  <T> T get(String key) {
        return get(key, null);
    }

    @Override
    public <T> T get(String key, Object defaultValue) {
        int index = keys.indexOf(key);
        if (index != -1) {
            CacheItem item = cacheItems.get(index);
            Object value = item.getValue();
            if (value == null) {
                this.remove(key);
            }
            return (T) value;
        }
        return (T) defaultValue;
    }

    @Override
    public void put(String key, Object value) {
        this.putValue(key, value,-1);
    }

    private synchronized void putValue(String key, Object value,int ttl) {
        if (this.exeits(key)) {
            this.cacheItems.add(this.keys.indexOf(key),new CacheItem(key, value, ttl));
        }else {
            this.keys.add(key);
            this.cacheItems.add(new CacheItem(key, value, ttl));
        }
        new Thread(new Cleaner());
    }

    @Override
    public void put(String key, Object value, int ttl) {
        this.putValue(key, value, ttl);
    }

    @Override
    public synchronized boolean remove(String key) {
        this.keys.remove(key);
        this.cacheItems.remove(new CacheItem(key));
        return false;
    }

    @Override
    public boolean exeits(String key) {
        return this.keys.indexOf(key) != -1;
    }

    @Override
    public int size() {
        return this.keys.size();
    }


    class Cleaner implements Runnable {
        @Override
        public void run() {
            int length = keys.size();
            int size = length/100;
            Random random = new Random();
            if (length > 100) {
                for (int i = 0; i < length; i++) {
                    int j = random.nextInt(length);
                    CacheItem item = cacheItems.get(j);
                    if (item.getTtl() != -1) {
                        if (item.isTimeOut()) {
                            remove(item.getKey());
                            logger.info("gc remove key:" + item.getKey());
                        }
                    }
                }
            }
        }
    }
}
