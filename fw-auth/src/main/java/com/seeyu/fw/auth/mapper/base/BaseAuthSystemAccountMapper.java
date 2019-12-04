package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthSystemAccount;

public interface BaseAuthSystemAccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(AuthSystemAccount record);

    int insertSelective(AuthSystemAccount record);

    AuthSystemAccount selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(AuthSystemAccount record);

    int updateByPrimaryKey(AuthSystemAccount record);
}