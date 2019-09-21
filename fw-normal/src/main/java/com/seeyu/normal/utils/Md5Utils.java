package com.seeyu.normal.utils;

import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    public static String encrypt(String data) throws Exception {
        return DigestUtils.md5DigestAsHex(data.getBytes("UTF-8"));
    }
}
