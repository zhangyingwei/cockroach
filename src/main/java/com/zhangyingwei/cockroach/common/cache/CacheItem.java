package com.zhangyingwei.cockroach.common.cache;

/**
 * Created by zhangyw on 2018/2/5.
 */
public class CacheItem {
    private String key;
    private Object value;
    private long createTime;
    private int ttl = -1;
    private long currentTime;

    public CacheItem(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public CacheItem(String key, Object value, int ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
        this.createTime = this.getCurrentTime();
    }

    public CacheItem(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getTtl() {
        return ttl;
    }

    public Object getValue() {
        if (!this.isTimeOut()) {
            return value;
        }else {
            return null;
        }
    }

    public boolean isTimeOut() {
        if (this.ttl > 0) {
            return this.createTime + ttl < this.getCurrentTime();
        }else {
            return false;
        }
    }

    public long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheItem)) return false;
        CacheItem item = (CacheItem) o;
        return getKey().equals(item.getKey());
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }
}
