package com.zhangyingwei.cockroach.common.cache;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2018/2/5.
 */
public class CockroachCacheTest {

    private CockroachCache cache;

    @Before
    public void before() throws Exception {
        cache = CockroachCache.getInstance();
        this.put();
    }

    @Test
    public void get() throws Exception {
        System.out.println(this.cache.size());
        System.out.println((String) this.cache.get("key99"));
        System.out.println(this.cache.size());
        System.out.println((String) this.cache.get("key200"));
        System.out.println(this.cache.size());
        while (true) {
            if (!this.cache.exeits("key210")) {
                System.out.println((String) this.cache.get("key210"));
                System.out.println("haha");
                break;
            }
        }
    }

    @Test
    public void put() throws Exception {
        for (int i = 0; i < 100; i++) {
            this.cache.put("key" + i, "value" + i);
        }

        for (int i = 100; i < 500; i++) {
            this.cache.put("key" + i, "value" + i, i);
        }
        System.out.println(this.cache.size());
    }

    @Test
    public void remove() throws Exception {
        System.out.println(this.cache.remove("key1"));
    }

    @Test
    public void exeits() throws Exception {
        System.out.println(this.cache.exeits("key1"));
    }

    @Test
    public void size() throws Exception {
        System.out.println(this.cache.size());
    }

}