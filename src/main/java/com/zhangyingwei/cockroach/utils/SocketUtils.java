package com.zhangyingwei.cockroach.utils;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zhangyw on 2017/8/11.
 */
public class SocketUtils {

    public static boolean valid(String ip,Integer potr){
        try {
            URL url = new URL("http://" + ip + ":" + potr);
            URLConnection conn = url.openConnection();
            return conn == null;
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:///iplist/IpStore-0.txt");
        Files.lines(Paths.get(file.toURI())).forEach(line -> {
            String[] ifs = line.split(":");
            String ip = ifs[0];
            Integer port = Integer.parseInt(ifs[1]);
            boolean res = SocketUtils.valid("113.121.252.24", 808);
            System.out.println(line+"->"+res);
        });
        System.out.println(SocketUtils.valid("117.158.198.74",9797));
    }
}
