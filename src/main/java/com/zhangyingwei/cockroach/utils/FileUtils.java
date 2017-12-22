package com.zhangyingwei.cockroach.utils;

import com.zhangyingwei.cockroach.common.generators.NameGenerator;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by zhangyw on 2017/10/18/018.
 * 操作文件的工具类
 */
public class FileUtils {

    /**
     * save bytes into file
     * @param bytes
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public static void save(byte[] bytes,String filePath,String fileName) throws IOException {
        Path path = Paths.get(filePath, fileName);
        mkirDirs(path.getParent());
        String pathStr = path.toString();
        File file = new File(pathStr);
        write(bytes,file);
    }

    /**
     * if dir is not exists,make it
     * @param parent
     */
    private static void mkirDirs(Path parent) {
        if(!parent.toFile().exists()){
            parent.toFile().mkdirs();
        }
    }

    /**
     * wtire bytes into file
     * @param bytes
     * @param file
     * @throws IOException
     */
    public static void write(byte[] bytes,File file) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        outputStream.write(bytes);
        outputStream.close();
    }

    /**
     * 获取文件名称
     * @param response
     * @return
     */
    public static String getFileName(TaskResponse response){
        String name = response.getResponse().header("content-disposition");
        if (null != name) {
            String[] names = name.split("' '");
            name = names[1];
            name = URLDecoder.decode(name);
        }
        return name;
    }

    /**
     * 获取文件名称，如果不存在，使用 UUID
     * @param response
     * @return
     */
    public static String getFileNameOrUuid(TaskResponse response) {
        String name = getFileName(response);
        if (null == name) {
            name = UUID.randomUUID().toString();
        }
        return name;
    }

    /**
     * 获取文件名称，如果获取不到，使用自定义接口生成一个名字
     * @param response
     * @param nameGenerator
     * @return
     */
    public static String getFileNameOr(TaskResponse response, NameGenerator nameGenerator) {
        String name = getFileName(response);
        if (null == name) {
            name = nameGenerator.name(response);
        }
        return name;
    }
}
