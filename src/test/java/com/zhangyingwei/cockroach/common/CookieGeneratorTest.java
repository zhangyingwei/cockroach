package com.zhangyingwei.cockroach.common;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/12/19.
 */
public class CookieGeneratorTest implements StringGenerator{

    @Override
    public String get() {
        String cookie = "v="+ UUID.randomUUID().toString();
        System.out.println(cookie);
        return cookie;
    }
}