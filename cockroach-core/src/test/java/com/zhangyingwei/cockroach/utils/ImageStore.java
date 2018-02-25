package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.common.generators.NameGenerator;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;

/**
 * Created by zhangyw on 2017/12/12.
 */
public class ImageStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        String name = FileUtils.getFileName(response);
        System.out.println(name);
        String name2 = FileUtils.getFileNameOrUuid(response);
//        System.out.println(name2);
        String name3 = FileUtils.getFileNameOr(response, new NameGenerator() {
            @Override
            public String name(TaskResponse response) {
                String url = response.getTask().getUrl();
//                url.split("/")
                return "generator";
            }
        });
//        System.out.println(name3);

//        System.out.println("use:"+name2);
//
//        FileUtils.save(response.getContentBytes(),"/Users/zhangyw/IdeaProjects/zhangyw/Projects/java/cockroach/src/main/resources",name2+".flv");
//        System.out.println("end");
    }
}
