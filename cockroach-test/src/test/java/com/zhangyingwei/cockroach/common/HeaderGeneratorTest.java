
package com.zhangyingwei.cockroach.common;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.executer.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/12/19.
 */
public class HeaderGeneratorTest implements MapGenerator {
    private Map headers = new HashMap();
    @Override
    public Map get(Task task) {
        if ("jobs.lagou".equals(task.getGroup())) {
            return headers;
        } else {
            System.out.println("text/json; charset=utf-8");
            headers.put("content-type", "text/json; charset=utf-8");
            return headers;
        }
    }
}