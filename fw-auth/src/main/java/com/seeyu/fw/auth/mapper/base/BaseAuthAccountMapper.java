package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthAccount;

public interface BaseAuthAccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(AuthAccount record);

    int insertSelective(AuthAccount record);

    AuthAccount selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(AuthAccount record);

    int updateByPrimaryKey(AuthAccount record);
}