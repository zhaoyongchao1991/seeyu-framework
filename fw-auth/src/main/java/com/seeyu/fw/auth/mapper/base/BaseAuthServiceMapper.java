package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthService;

public interface BaseAuthServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(AuthService record);

    int insertSelective(AuthService record);

    AuthService selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(AuthService record);

    int updateByPrimaryKey(AuthService record);
}