package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthResource;

public interface BaseAuthResourceMapper {
    int deleteByPrimaryKey(Integer resourceId);

    int insert(AuthResource record);

    int insertSelective(AuthResource record);

    AuthResource selectByPrimaryKey(Integer resourceId);

    int updateByPrimaryKeySelective(AuthResource record);

    int updateByPrimaryKey(AuthResource record);
}