package com.zhangyingwei.cockroach.utils;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/10/18/018.
 */
public class SpyderUtils {

    /**
     * save bytes as a file
     * @param bytes
     * @param path
     * @param fileName
     * @throws IOException
     */
    public static void saveAsFile(byte[] bytes,String path,String fileName) throws IOException {
        FileUtils.save(bytes, path, fileName);
    }

    /**
     * find add urls from content
     * @param content
     * @return
     */
    public static List<String> findAllUrls(String content){
        return Jsoup.parse(Optional.ofNullable(content).orElse("")).select("a").stream().map(a -> {
            return a.attr("href");
        }).collect(Collectors.toList());
    }
}
