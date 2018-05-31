package com.zhangyingwei.cockroach.queue;

import com.zhangyingwei.cockroach.executer.task.Task;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2018/2/27.
 */
public class RedisTaskQueue extends AbstractCockroachQueue {
    private String key;
    private String failedKey;
    private Jedis jedis;

    public RedisTaskQueue(String host, Integer port,String key,String failedKey) {
        this.key = key;
        this.failedKey = failedKey;
        this.jedis = new Jedis(host, port);
    }

    public RedisTaskQueue(String host, Integer port, String auth, String key,String failedKey) {
        this.key = key;
        this.failedKey = failedKey;
        this.jedis = new Jedis(host, port);
        this.jedis.auth(auth);
    }

    public RedisTaskQueue(String host, Integer port, String auth, Integer index, String key,String failedKey) {
        this.key = key;
        this.failedKey = failedKey;
        this.jedis = new Jedis(host, port);
        this.jedis.auth(auth);
        this.jedis.select(index);
    }

    public static RedisTaskQueue of(String host, Integer port,String key,String failedKey){
        return new RedisTaskQueue(host,port,key,failedKey);
    }

    public static RedisTaskQueue of(String host, Integer port,String auth,String key,String failedKey){
        return new RedisTaskQueue(host, port, auth, key, failedKey);
    }

    public static RedisTaskQueue of(String host, Integer port,String auth,Integer index,String key,String failedKey){
        return new RedisTaskQueue(host, port, auth, index, key, failedKey);
    }

    @Override
    public synchronized Task poll() throws Exception {
        String json = this.jedis.lpop(this.key);
        JSONObject jsonObject = JSONObject.fromObject(json);
        return (Task) JSONObject.toBean(jsonObject, Task.class);
    }

    @Override
    public synchronized Task take() throws Exception {
        List<String> json = this.jedis.blpop(Integer.MAX_VALUE,this.key);
        JSONObject jsonObject = JSONObject.fromObject(json.get(1));
        return (Task) JSONObject.toBean(jsonObject, Task.class);
    }

    @Override
    public void push(Task task) throws Exception {
        this.push(task,true);
    }

    @Override
    public void push(Task task, Boolean withFilter) throws Exception {
        if (withFilter) {
            if (super.filterBox.accept(task)) {
                JSONObject json = JSONObject.fromObject(task);
                this.jedis.lpush(this.key, json.toString());
            }
        } else {
            JSONObject json = JSONObject.fromObject(task);
            this.jedis.lpush(this.key, json.toString());
        }
    }

    @Override
    public void falied(Task task) throws Exception {
        JSONObject json = JSONObject.fromObject(task);
        this.jedis.lpush(this.failedKey, json.toString());
    }

    @Override
    public void pushAll(List<Task> tasks) throws Exception {
        for (Task task : tasks) {
            push(task);
        }
    }

    @Override
    public void push(List<String> urls) throws Exception {
        List<Task> tasks = urls.stream().map(url -> {
            return new Task(url);
        }).collect(Collectors.toList());
        pushAll(tasks);
    }

    @Override
    public void clear() throws Exception {
        this.jedis.del(this.key);
        this.jedis.del(this.failedKey);
    }

    @Override
    public Boolean isEmpty() {
        long size = this.jedis.llen(this.key);
        long faildSize = this.jedis.llen(this.failedKey);
        return size + faildSize == 0;
    }
}
