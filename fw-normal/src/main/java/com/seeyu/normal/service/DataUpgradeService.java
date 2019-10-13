package com.seeyu.normal.service;

import com.seeyu.core.utils.Assert;
import com.seeyu.lang.utils.FileUtils;
import com.seeyu.lang.utils.StringUtils;
import com.seeyu.normal.dao.mapper.DbVersionMapper;
import com.seeyu.normal.entity.DbVersion;
import com.seeyu.normal.initialization.DatabaseUpgradeInitialization;
import com.seeyu.normal.service.helper.SqlTextParseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/6/3
 */
@Slf4j
@Service
public class DataUpgradeService {

    public static final String DB_VERSION_TABLE_NAME = "db_version";

    @Autowired
    private MysqlExecuteService mysqlExecuteService;
    @Autowired
    DbVersionMapper dbVersionMapper;

    @Transactional(rollbackFor = Exception.class)
    public void processDataUpgrade(List<String> sqlScripts) throws Exception {
        initValidateDbVersion();
        String dbVersion = getCurrentVersion();
        for(String sqlFilePath : sqlScripts){
            String targetVersion = FileUtils.extractSmallFileName(sqlFilePath).trim();
            if(dbVersion == null || targetVersion.compareToIgnoreCase(dbVersion) > 0){
                updateDbVersionTarget(dbVersion, targetVersion);
                executeSqlScript(sqlFilePath);
                updateDbVersion(dbVersion, targetVersion);
                dbVersion = targetVersion;
            }
        }
        DatabaseUpgradeInitialization.setCurrentVersion(dbVersion);
    }


    private void executeSqlScript(String path) throws Exception {
        SqlTextParseHelper sqlTextParseHelper = new SqlTextParseHelper(path);
        List<String> sqlList = sqlTextParseHelper.parseSqlFile();
        log.info("从文件{}中解析出sql：\n{}", path, StringUtils.StringArrays2String(sqlList.toArray(new String[0]), "\n\n"));
        for(String sql : sqlList){
            try{
                mysqlExecuteService.executeSql(sql);
            }
            catch(Exception e){
                log.error("sql执行异常：\n{}", sql);
                throw e;
            }
        }
    }


    private String getCurrentVersion(){
        if(mysqlExecuteService.tableExists(DB_VERSION_TABLE_NAME)){
            return dbVersionMapper.getCurrentVersion();
        }
        else{
            return null;
        }
    }


    private void updateDbVersionTarget(String version, String target){
        if(version == null){
            dbVersionMapper.insertDbVersionTarget(target, new Date());
        }
        else{
            dbVersionMapper.updateDbVersionTarget(target, new Date());
        }
    }

    private void updateDbVersion(String from, String version) throws Exception {
        validateVersion(from, version);
        dbVersionMapper.updateDbVersion(version, new Date());
    }


    private void validateVersion(String from, String to){
        Assert.notNull(to, "升级版本不能为：null");
        Assert.ASSERT( from == null || to.compareToIgnoreCase(from) > 0, "当前版本：{}高于目标版本：{}，无法升级", from, to);
        String v = getCurrentVersion();
        boolean valid = from == null && v == null || from != null && from.equals(v);
        Assert.ASSERT(valid, "数据升级版本异常：当前版本{}，校验版本{}",v, from);
    }


    private void initValidateDbVersion(){
        if(!mysqlExecuteService.tableExists(DB_VERSION_TABLE_NAME)){
            String sql = "CREATE TABLE `db_version` (\n" +
                    " `id` int(11) NOT NULL,\n" +
                    "  `version` varchar(20) DEFAULT NULL,\n" +
                    "  `target` varchar(20) NOT NULL,\n" +
                    "  `time` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            mysqlExecuteService.executeSql(sql);
        }
        else{
            DbVersion dbVersion = dbVersionMapper.getDbVersion();
            if(dbVersion == null || !dbVersion.getVersion().equals(dbVersion.getTarget())){
                throw new RuntimeException("之前的数据未升级成功");
            }
        }
    }

}
