package com.zhangyingwei.cockroach.samples.douban.movie.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.samples.douban.movie.Movie;
import com.zhangyingwei.cockroach.store.IStore;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by zhangyw on 2018/2/28.
 */
public class DMovieStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("douban.movie")) {
            JSONObject resJson = response.getContent().toJsonObject();
            JSONArray subjects = resJson.getJSONArray("data");
            for (Object subject : subjects) {
                JSONObject movie = JSONObject.fromObject(subject);
                Movie movieObj = new Movie();
                movieObj.setId(movie.getString("id"));
                movieObj.setTitle(movie.getString("title"));
                movieObj.setRate(movie.getString("rate"));
                movieObj.setUrl(movie.getString("url"));
                movieObj.setDirectors(movie.getJSONArray("directors"));
                movieObj.setCasts(movie.getJSONArray("casts"));
                System.out.println(movieObj);
            }
            if (subjects.size() > 0) {
                Task task = new Task();
                task.setUrl(response.getTask().getUrl());
                task.setGroup(response.getTask().getGroup());
                Map<String, Object> params = response.getTask().getParams();
                Integer page_start = (Integer) params.get("start");
                params.put("start", page_start + 20);
                task.setParams(params);
                response.getQueue().push(task);
            }
        }
    }
}
