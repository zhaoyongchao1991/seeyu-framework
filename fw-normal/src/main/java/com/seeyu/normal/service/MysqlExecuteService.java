package com.seeyu.normal.service;

import com.seeyu.normal.dao.mapper.SqlExecuteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seeyu
 * @date 2019/6/3
 */
@Service
public class MysqlExecuteService {

    @Autowired
    private SqlExecuteMapper mysqlExecuteMapper;


    public boolean tableExists(String tableName) {
        return 1 == mysqlExecuteMapper.tableExists(tableName);
    }


    @Transactional(rollbackFor = Exception.class)
    public void executeSql(String sql){
        this.mysqlExecuteMapper.executeSql(sql);
    }

}
