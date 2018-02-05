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

    public CockroachCache() {
        this.init();
    }

    private void init() {
        this.keys = new ArrayList<String>();
        this.cacheItems = new ArrayList<CacheItem>();
        new Thread(new Cleaner()).start();
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
        return this.keys.contains(key);
    }

    @Override
    public int size() {
        return this.keys.size();
    }


    class Cleaner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    Iterator<CacheItem> it = cacheItems.iterator();
                    while (it.hasNext()) {
                        CacheItem item = it.next();
                        if (item.getTtl() != -1) {
                            if (item.isTimeOut()) {
                                keys.remove(item.getKey());
                                it.remove();
                                logger.info("gc remove key:" + item.getKey());
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
