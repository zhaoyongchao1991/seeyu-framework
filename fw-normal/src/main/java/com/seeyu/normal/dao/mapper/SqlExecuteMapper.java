package com.seeyu.normal.dao.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author seeyu
 * @date 2019/6/3
 */
public interface SqlExecuteMapper {


    @ResultType(Integer.class)
    @Select("select count(1) as count from information_schema.`TABLES` t where t.TABLE_SCHEMA=SCHEMA() and t.TABLE_NAME=#{tableName,jdbcType=VARCHAR}")
    int tableExists(String tableName);


    @Update("${sql}")
    void executeSql(@Param("sql") String sql);


}
