package com.seeyu.normal.utils;

import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author seeyu
 * @date 2019/7/16
 */
public class Md5Utils {


    public static String encrypt(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        try{
            return DigestUtils.md5DigestAsHex(fin);
        }
        finally {
            fin.close();
        }
    }

    public static String encrypt(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String encrypt(String data, String salt) {
        return DigestUtils.md5DigestAsHex((data + salt).getBytes(StandardCharsets.UTF_8));
    }
}
