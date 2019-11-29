package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthGroupRelRole;

public interface BaseAuthGroupRelRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthGroupRelRole record);

    int insertSelective(AuthGroupRelRole record);

    AuthGroupRelRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthGroupRelRole record);

    int updateByPrimaryKey(AuthGroupRelRole record);
}