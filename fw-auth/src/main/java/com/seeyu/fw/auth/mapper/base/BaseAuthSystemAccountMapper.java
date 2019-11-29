package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthSystemAccount;

public interface BaseAuthSystemAccountMapper {
    int insert(AuthSystemAccount record);

    int insertSelective(AuthSystemAccount record);
}