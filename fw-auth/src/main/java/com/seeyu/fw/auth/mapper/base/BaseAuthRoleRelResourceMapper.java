package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthRoleRelResource;

public interface BaseAuthRoleRelResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleRelResource record);

    int insertSelective(AuthRoleRelResource record);

    AuthRoleRelResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRoleRelResource record);

    int updateByPrimaryKey(AuthRoleRelResource record);
}