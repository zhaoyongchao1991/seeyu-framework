package com.seeyu.lang.utils;

import java.io.*;
import java.nio.file.Path;

/**
 * @author seeyu
 */
public class FileUtils {

    private static final String CLASSPATH_PREFIX = "classpath:";

    /**
     * 删除文件
     * @param file
     */
    public static void delete(File file){
        if(file == null){
            return;
        }
        if(file.isFile()){
            if(file.exists()){
                if(!file.delete()){
                    file.deleteOnExit();
                }
            }
        }
        else if(file.isDirectory()){
            String[] ss = file.list();
            if(ss == null || ss.length == 0){
                file.delete();
            }
        }
    }

    /**
     * 删除文件
     * @param files
     */
    public static void delete(File[] files){
        for(File file : files){
            if(file != null){
                delete(file);
            }
        }
    }


    /**
     * 向文件中写入文本
     * @param path
     * @param text
     * @throws IOException
     */
    public static void writeText(String path, String text) throws IOException {
        writeText(new File(path), text);
    }

    /**
     * 向文件中写入文本
     * @param file
     * @param text
     * @throws IOException
     */
    public static void writeText(File file, String text) throws IOException {
        FileWriter fw = null;
        try{
            fw = new FileWriter(file);
            fw.write(text);
        }
        finally {
            IOUtils.closeStream(fw);
        }
    }

    /**
     * 文件另存为
     * @param path
     * @param as
     */
    public static void saveAs(String path, String as) throws IOException {
        saveAs(new File(path), new File(as));
    }

    /**
     * 文件另存为
     * @param file
     * @param as
     */
    public static void saveAs(File file, String as) throws IOException {
        saveAs(file, new File(as));
    }


    /**
     * 另存为
     * @param file
     * @param as
     */
    public static void saveAs(File file, File as) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        try{
            in = new FileInputStream(file);
            out = new FileOutputStream(as);
            IOUtils.copyBytes(in, out, false);
        }
        finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }

    /**
     * 另存为
     * @param in
     * @param as
     */
    public static void saveAs(InputStream in, File as) throws IOException {
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(as);
            IOUtils.copyBytes(in, out, false);
        }
        finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }

    /**
     * 另存为
     * @param in
     * @param out
     */
    public static void saveAs(InputStream in, OutputStream out) throws IOException {
        try{
            IOUtils.copyBytes(in, out, false);
        }
        finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }


    public static String extractExtensionName(File file){
        return extractExtensionName(file.getPath());
    }

    public static String extractExtensionName(String path){
        return extractExtensionName(path, false);
    }

    public static String extractExtensionName(File file, boolean upperCase){
        return extractExtensionName(file.getPath(), upperCase);
    }

    /**
     * 抽取扩展名
     * e:\\test.txt => txt
     * @param path
     * @param upperCase
     * @return
     */
    public static String extractExtensionName(String path, boolean upperCase){
        if(!path.contains(".")){
            return null;
        }
        else{
            String extension = path.substring(path.lastIndexOf(".") + 1);
            return upperCase ? extension.toUpperCase() : extension;
        }
    }

    /**
     * 抽取文件名, 包含扩展名
     * e:\\test.txt => test.txt
     * @param path
     * @return
     */
    public static String extractFileName(String path){
        if(path.startsWith(CLASSPATH_PREFIX)){
            path = path.substring(CLASSPATH_PREFIX.length());
        }
        int index = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
        if(index != -1){
            path = path.substring(index + 1);
        }
        return path;
    }

    /**
     * 抽取文件名, 不包含扩展名
     * e:\\test.txt => test.txt
     * @param path
     * @return
     */
    public static String extractSmallFileName(String path){
        String fileName = extractFileName(path);
        return fileName.substring(0,fileName.lastIndexOf(".") );
    }

    /**
     * 替换文件名部分, 不包括扩展名
     *  source=e:\\test.txt; target=sample; => sample.txt
     * @param source
     * @param target
     * @return
     */
    public static String replaceFileName(String source, String target){
        String extension = extractExtensionName(source, false);
        if(StringUtils.isNotBlank(extension)){
            return target + "." + extension;
        }
        else{
            return target;
        }
    }

    /**
     * 获取文件的编码方式
     * @param sourceFile
     * @return
     */
    public static String getFileCharset(File sourceFile) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                //文件编码为 ANSI
                return charset;
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                //文件编码为 Unicode
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                //文件编码为 Unicode big endian
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                //文件编码为 UTF-8
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0){
                        break;
                    }
                    // 单独出现BF以下的，也算是GBK
                    if (0x80 <= read && read <= 0xBF){
                        break;
                    }
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        // 双字节 (0xC0 - 0xDF)
                        if (0x80 <= read && read <= 0xBF){
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        }
                        else{
                            break;
                        }
                    }
                    // 也有可能出错，但是几率较小
                    else if (0xE0 <= read && read <= 0xEF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else{
                                break;
                            }

                        } else{
                            break;
                        }
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

}
