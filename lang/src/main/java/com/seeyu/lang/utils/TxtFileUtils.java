package com.seeyu.lang.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author seeyu
 * @date 2019/2/26
 */
@Slf4j
public class TxtFileUtils {

    /**
     * 读取文件中的文本
     * @param file
     * @return
     * @throws IOException
     */
    public static String readText(File file) throws IOException {
        BufferedReader br = null;
        try{
            String encoding = FileUtils.getFileCharset(file);
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            String line = null;
            while((line = br.readLine())!= null){
                sb.append(line).append("\n");
            }
            String text = null;
            if (sb.length() > 0 && (int) sb.charAt(0) == 65279) {
                text = sb.substring(1);
            } else {
                text = sb.toString();
            }
            return text;
        }
        finally {
            IOUtils.closeStream(br);
        }
    }


}
