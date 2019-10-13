package com.seeyu.normal.dao.mapper;

import com.seeyu.normal.entity.DbVersion;
import com.seeyu.normal.service.DataUpgradeService;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * @author seeyu
 * @date 2019/6/4
 */
public interface DbVersionMapper {



    @ResultType(String.class)
    @Select("select version from " + DataUpgradeService.DB_VERSION_TABLE_NAME)
    String getCurrentVersion();


    @Select("select * from " + DataUpgradeService.DB_VERSION_TABLE_NAME)
    @ResultType(DbVersion.class)
    DbVersion getDbVersion();


    @Insert("insert into " + DataUpgradeService.DB_VERSION_TABLE_NAME + "(id, target, time) values (1, #{target,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP})")
    void insertDbVersionTarget(@Param("target") String target, @Param("time") Date time);


    @Update("update " + DataUpgradeService.DB_VERSION_TABLE_NAME + " set target = #{target,jdbcType=VARCHAR}, time=#{time,jdbcType=TIMESTAMP} where id=1")
    void updateDbVersionTarget(@Param("target") String target, @Param("time") Date time);


    @Update("update " + DataUpgradeService.DB_VERSION_TABLE_NAME + " set version = #{version,jdbcType=VARCHAR}, time=#{time,jdbcType=TIMESTAMP} where id=1")
    void updateDbVersion(@Param("version") String version, @Param("time") Date time);


}
