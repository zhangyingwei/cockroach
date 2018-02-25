package com.zhangyingwei.cockroach.executer.response.filter;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;

import java.io.IOException;

/**
 * @author: zhangyw
 * @date: 2018/1/24
 * @time: 下午3:39
 * @desc:
 */
public class ResponseFilterTest implements ITaskResponseFilter{
    @Override
    public boolean accept(TaskResponse response) {
        try {
            return response.select("title").text().contains("百度");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
