package com.zhangyingwei.cockroach.common.utils;

import com.zhangyingwei.cockroach.common.generators.NameGenerator;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import org.apache.log4j.Logger;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/10/18/018.
 * 操作文件的工具类
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);
    private static Map<String, Writer> writerCache = new HashMap<String, Writer>();

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
        List<String> name = response.header("content-disposition");
        System.out.println(name);
        if (null != name) {
            return Arrays.stream(name.get(0).split("; ")).filter(item -> {
                return item.trim().startsWith("filename=\"");
            }).map(filename -> {
                return filename.replaceAll("filename=","").replaceAll("\"","");
            }).limit(1).collect(Collectors.toList()).get(0);
        }
        return null;
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
            name = nameGenerator.name();
        }
        return name;
    }

    /**
     * 打开或者创建
     * 如果存在就打开，如果不存在就创建
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File openOrCreate(String path,String fileName) throws IOException {
        Path filePath = Paths.get(path, fileName);
        File file = filePath.toFile();
        if (file.exists()) {
            if (file.isFile()) {
                return file;
            }
            throw new FileNotFoundException(path+" is discroty");
        }else {
            mkirDirs(filePath.getParent());
            file.createNewFile();
            return file;
        }
    }

    /**
     * 文件中追加内容
     * @param file
     * @param content
     * @throws IOException
     */
    public synchronized static void append(File file,String content) throws IOException {
        Writer writer = writerCache.getOrDefault(file.getPath(), new FileWriter(file,true));
        writer.write(content);
        writer.flush();
        writerCache.put(file.getPath(), writer);
    }

    /**
     * 关闭所有 writer
     */
    public synchronized static void closeWriters() {
        writerCache.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writerCache.clear();
    }

    /**
     * 关闭相应 file 的 writer
     * @param filePath
     */
    public static void closeWriter(String filePath) {
        Writer writer = writerCache.get(filePath);
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writerCache.remove(writer);
    }

    /**
     * 清空文件内容
     * @param file
     * @throws IOException
     */
    public static void clearFile(File file) throws IOException {
        write(new byte[0],file);
    }

    /**
     * delete file
     * @param file
     */
    public static boolean delete(File file) {
        boolean result = false;
        if (file.exists()) {
            result = file.delete();
            if (!result) {
                logger.info("delete " + file.getName() + " error");
            }
        }
        return result;
    }
}
