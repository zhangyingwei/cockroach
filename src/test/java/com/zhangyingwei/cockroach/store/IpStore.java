package com.zhangyingwei.cockroach.store;

import com.zhangyingwei.cockroach.executer.TaskResponse;
import com.zhangyingwei.cockroach.utils.NameUtils;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class IpStore implements IStore {

    private String id = NameUtils.name(IpStore.class);

    public IpStore() throws IOException {}

    @Override
    public void store(TaskResponse response) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("D://iplist/"+id+".txt",true),true);
        Elements els = response.select("#ip_list tr");
        els.stream().filter(el -> el.select("td").size()>2).map(el -> {
            Elements es = el.select("td");
            List<String> texts = es.stream().map(td -> td.text()).filter(text -> text.trim().length() > 0).collect(Collectors.toList());
            String ip = "";
            String port = "";
            if(texts.size()>2){
                ip = texts.get(0);
                port = texts.get(1);
            }
            return new String[]{ip,port};
        }).forEach(name -> {
            System.out.println(Arrays.toString(name));
            String line = name[0] + ":" + name[1];
            writer.println(line);
        });
        writer.close();
    }
}
