package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.Task;

import java.util.List;

/**
 * Created by zhangyw on 2017/9/13.
 * 队列接口
 */
public interface CockroachQueue {

    /**
     * 如果队列为空 结果为 null
     * @return
     * @throws Exception
     */
    public Task poll() throws Exception;

    /**
     * 如果队列为空 阻塞等待 直到队列不为空
     * @return
     * @throws Exception
     */
    public Task take() throws Exception;

    /**
     * 入队
     * @param task
     * @throws Exception
     */
    public void push(Task task) throws Exception;

    /**
     * 批量入队
     * @param tasks
     * @throws Exception
     */
    public void pushAll(List<Task> tasks) throws Exception;

    /**
     * 批量入队
     * @param urls
     * @throws Exception
     */
    public void push(List<String> urls) throws Exception;

    /**
     * 清空队列
     * @throws Exception
     */
    public void clear() throws Exception;

    /**
     * push 的时候先经过过滤器
     * @throws Exception
     */
    public CockroachQueue filter(IQueueTaskFilter filter) throws Exception;
}
