package com.seeyu.normal.config;

import com.seeyu.normal.MySpringApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author seeyu
 * @date 2019/6/11
 */
@Slf4j
public class GlobalConfig {


    public static final String CLASSPATH_PREFIX = "classpath:";



    /**
     * 两种加载方式
     * 如果以 CLASSPATH_PREFIX开头 则从项目根路径加载，否则从文件系统加载
     * @param path
     */
    public static InputStream loadResourceAsStream(String path){
        if(path.startsWith(CLASSPATH_PREFIX)){
            return loadClassPathResourceAsStream(path.substring(CLASSPATH_PREFIX.length()));
        }
        else{
            try {
                return new FileInputStream(path);
            }
            catch (FileNotFoundException e){
                log.info("找不到文件:{}", path);
                return null;
            }
        }
    }

    /**
     * 加载classpath下的资源，如果项目被打包成了jar文件，则优先读取jar包同目录下的资源文件
     * @param subPath
     * @return
     */
    public static InputStream loadClassPathResourceAsStream(String subPath) {
        if(!(subPath.startsWith("/") && subPath.startsWith("\\"))){
            subPath = "/" + subPath;
        }
        try{
            URL projectUrl = getProtectionLocation();
            //如果是jar包，则优先从classpath下读取文件
            if(ResourceUtils.isJarFileURL(projectUrl)){
                File file = new File(getClassPath(), subPath);
                if(file.exists()){
                    return new FileInputStream(file);
                }
            }
            return MySpringApplication.class.getResourceAsStream(subPath);
        }
        catch (FileNotFoundException e){
            log.info("从classpath下找不到文件:{}", subPath);
            return null;
        }
    }

    /**
     * 获取项目路径 有可能是jar
     * @return
     */
    public static URL getProtectionLocation(){
        return MySpringApplication.mainApplicationClass.getProtectionDomain().getCodeSource().getLocation();
    }

    /**
     * 获取项目根路径
     * @return
     */
    public static String getClassPath(){
        String path = MySpringApplication.mainApplicationClass.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(path);
        if(file.isDirectory()){
            return file.getPath();
        }
        else{
            return file.getParent();
        }
    }

    /**
     * 获取资源路径
     * @return
     */
    public static String getResourcePath(String path){
        if(path.startsWith(CLASSPATH_PREFIX)){
            return new File(getClassPath(), path.substring(CLASSPATH_PREFIX.length())).getPath();
        }
        return new File(path).getPath();
    }



}
