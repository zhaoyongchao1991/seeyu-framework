package com.seeyu.normal.initialization;

import com.seeyu.normal.config.GlobalConfig;
import com.seeyu.normal.service.DataUpgradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author seeyu
 * @date 2019/5/15
 */
@Slf4j
@Configuration
public class DatabaseUpgradeInitialization {

    private static String currentVersion;

    private static final String ROOT_FOLDER_NAME = "databaseScript";
    private static final String SQL_SCRIPT_FILE_EXTENSIONS = ".sql";

    @Autowired
    private DataUpgradeService dataUpgradeService;

    @PostConstruct
    public void run() {
        try {
            processDataUpgrade();
        } catch (Exception e) {
            throw new RuntimeException("升级数据库数据失败，请恢复数据库后再启动服务", e);
        }
    }

    public static void setCurrentVersion(String version){
        currentVersion = version;
    }

    public static String getCurrentVersion(){
        return currentVersion;
    }

    private void processDataUpgrade() throws Exception {
        List<String> sqlScriptList  = getDbScriptResources();
        if(sqlScriptList.size() == 0){
            log.info("未找到sql脚本，退出数据升级");
            return;
        }
        log.info("找到sql脚本文件：{}", sqlScriptList);
        Collections.sort(sqlScriptList);
        log.info("升级顺序：{}", sqlScriptList);
        this.dataUpgradeService.processDataUpgrade(sqlScriptList);
    }

    private List<String> getDbScriptResources() throws IOException {
        List<String> resources = new LinkedList<>();
        URL domainUrl = GlobalConfig.getProtectionLocation();
        if(ResourceUtils.isJarFileURL(domainUrl)){
            log.info("从目录{}下搜索{}文件", domainUrl, ROOT_FOLDER_NAME + "/*" +SQL_SCRIPT_FILE_EXTENSIONS);
            JarFile localJarFile = new JarFile(domainUrl.getPath());
            Enumeration<JarEntry> entries = localJarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                if(!jarEntry.isDirectory() && jarEntry.getName().startsWith(ROOT_FOLDER_NAME + "/") && jarEntry.getName().endsWith(SQL_SCRIPT_FILE_EXTENSIONS)){
                    resources.add(jarEntry.getName());
                }
            }
            return resources;
        }
        else{
            File rootFile = new File(domainUrl.getFile(), ROOT_FOLDER_NAME);
            log.info("从目录{}下搜索{}文件", rootFile.getPath(), SQL_SCRIPT_FILE_EXTENSIONS);
            if(rootFile.exists()){
                String[] files = rootFile.list((dir, name) -> name.endsWith(SQL_SCRIPT_FILE_EXTENSIONS));
                if(files != null){
                    for(String f : files){
                        resources.add(ROOT_FOLDER_NAME + File.separator + f);
                    }
                    return resources;
                }
            }
        }
        return resources;
    }

}
