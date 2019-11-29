package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthAccountRelRole;

public interface BaseAuthAccountRelRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthAccountRelRole record);

    int insertSelective(AuthAccountRelRole record);

    AuthAccountRelRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthAccountRelRole record);

    int updateByPrimaryKey(AuthAccountRelRole record);
}