package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthGroup;

public interface BaseAuthGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(AuthGroup record);

    int insertSelective(AuthGroup record);

    AuthGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(AuthGroup record);

    int updateByPrimaryKey(AuthGroup record);
}