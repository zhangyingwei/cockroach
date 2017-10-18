package com.zhangyingwei.cockroach.utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
