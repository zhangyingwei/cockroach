package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyw
 * @date: 2018/9/4
 * @desc:
 */
public class CockroachContextGithubIssueTest {
    @Test
    public void test(){}
    public static final CockroachQueue queue = TaskQueue.of();
    public static void main(String[] args) throws Exception {
        System.err.println("1111111111111");//下面的代码都不执行
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("sort", "T");
        params.put("range", "0,20");
        params.put("tag", "");
        params.put("start", 0);
        Task task = new Task("https://movie.douban.com/j/new_search_subjects", "douban.movie", params);
        queue.push(task);
        // CockroachApplication.run(DMovieApplication.class, queue);
        Thread.sleep(5000);
        queue.push(new Task("https://timeline-merger-ms.juejin.im/v1/get_entry_by_rank?src=web&uid=58368a0461ff4b475bd600bc&device_id=1519648660286&token=eyJhY2Nlc3NfdG9rZW4iOiJqQVlaSWxIN1U3aGJnV3YzIiwicmVmcmVzaF90b2tlbiI6IlV5RnA3eDFaVWp4bk9jRVEiLCJ0b2tlbl90eXBlIjoibWFjIiwiZXhwaXJlX2luIjoyNTkyMDAwfQ%3D%3D&limit=20&category=all&recomment=1"));

    }
}
