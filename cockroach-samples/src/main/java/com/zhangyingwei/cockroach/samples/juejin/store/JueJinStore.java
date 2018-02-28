package com.zhangyingwei.cockroach.samples.juejin.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import net.sf.json.JSONObject;

/**
 * @author: zhangyw
 * @date: 2018/2/26
 * @time: 下午8:49
 * @desc:
 */
public class JueJinStore implements IStore{
    @Override
    public void store(TaskResponse response) throws Exception {
        response.getContent().toJsonObject()
                .getJSONObject("d")
                .getJSONArray("entrylist")
                .forEach(item -> {
                    JSONObject itemJson = JSONObject.fromObject(item);
                    System.out.println(itemJson.getString("title"));
                });
    }
}
