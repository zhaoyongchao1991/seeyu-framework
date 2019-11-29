package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthGroupRelAccount;

public interface BaseAuthGroupRelAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthGroupRelAccount record);

    int insertSelective(AuthGroupRelAccount record);

    AuthGroupRelAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthGroupRelAccount record);

    int updateByPrimaryKey(AuthGroupRelAccount record);
}