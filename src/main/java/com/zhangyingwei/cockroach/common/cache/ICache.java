package com.zhangyingwei.cockroach.common.cache;

/**
 * Created by zhangyw on 2018/2/5.
 */
public interface ICache {
    <T> T get(String key);

    <T>T get(String key,Object defaultValue);

    void put(String key,Object value);

    void put(String key, Object value, int ttl);

    boolean remove(String key);

    boolean exeits(String key);

    int size();
}
