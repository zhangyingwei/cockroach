
package com.zhangyingwei.cockroach.common;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/12/19.
 */
public class HeaderGeneratorTest implements MapGenerator {
    private Map headers = new HashMap();
    @Override
    public Map get(Task task) {
        return headers;
    }
}