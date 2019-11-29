package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthMenuRelResource;

public interface BaseAuthMenuRelResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthMenuRelResource record);

    int insertSelective(AuthMenuRelResource record);

    AuthMenuRelResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthMenuRelResource record);

    int updateByPrimaryKey(AuthMenuRelResource record);
}