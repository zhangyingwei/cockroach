package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;

/**
 * @author: zhangyw
 * @date: 2018/1/21
 * @time: 下午3:06
 * @desc:
 */
public class SelecterTestStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        String title = response.select("title").text();
        System.out.println(title);
        String res = response.xpath("//*[@id='cnblogs_post_body']/h2").get(2).text();
        System.out.println(res);
    }
}
