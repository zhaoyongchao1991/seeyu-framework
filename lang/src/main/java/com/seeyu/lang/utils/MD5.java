package com.seeyu.lang.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


    /**
     * 文件流释放有问题
     * @param file
     * @return
     */
    @Deprecated
    public static String encrypt(File file) {
        String value = null;
        FileInputStream in = null;
        FileChannel channel = null;
        try {
            in = new FileInputStream(file);
            channel = in.getChannel();
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
            if (value.length() < 32) {
                value = "0" + value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value.toLowerCase();
    }


    public static String encrypt(String data) {
       return encrypt(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String encrypt(byte[] data) {
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data);
            BigInteger bi = new BigInteger(1, md5.digest());
            String returnStr = bi.toString(16);
            while (returnStr.length() < 32) {
                returnStr = "0" + returnStr;
            }
            return returnStr;
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
