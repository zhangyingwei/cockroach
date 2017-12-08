package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.config.CockroachConfigBuilder;
import com.zhangyingwei.cockroach.queue.CockroachQueue;

import java.lang.annotation.Annotation;

/**
 * Created by zhangyw on 2017/12/8.
 */
public class CockroachApplication {
    public static void run(Class clazz, CockroachQueue queue) throws Exception {
        Annotation[] annotations = clazz.getAnnotations();
        CockroachConfig config = new CockroachConfigBuilder(annotations).bulid();
        CockroachContext context = new CockroachContext(config);
        context.start(queue);
    }
}
