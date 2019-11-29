package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthRole;

public interface BaseAuthRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(AuthRole record);

    int insertSelective(AuthRole record);

    AuthRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(AuthRole record);

    int updateByPrimaryKey(AuthRole record);
}