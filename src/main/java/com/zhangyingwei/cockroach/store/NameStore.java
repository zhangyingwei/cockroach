package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.utils.NameUtils;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class NameStore implements IStore {

    private String id = NameUtils.name(NameStore.class);

    public NameStore() throws IOException {}

    @Override
    public void store(TaskResponse response) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("D://"+id+".txt",true),true);
        Elements els = response.select("strong");
        els.stream().map(el -> el.text().trim())
                .filter(name -> !name.contains("第"))
                .filter(name -> !name.startsWith("热门"))
                .filter(name -> !name.startsWith("找动画"))
                .filter(name -> !name.startsWith("凹凸"))
                .filter(name -> !name.contains("更多"))
                .filter(name -> !name.contains("5068"))
                .filter(name -> !name.contains("热播动画"))
                .filter(name -> !name.contains("点击浏览"))
                .filter(name -> !name.contains("上一页"))
                .filter(name -> !name.contains("关于我们"))
                .filter(name -> name.length() > 0)
                .map(name -> name.split("（")[0].trim().replaceAll("　",""))
                .forEach(name -> {
                    System.out.println(id+":"+name);
                    writer.println(name);
                });
        writer.close();
    }
}
