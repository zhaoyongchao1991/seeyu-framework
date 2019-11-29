package com.seeyu.fw.auth.mapper.base;

import com.seeyu.fw.auth.entity.AuthMenu;

public interface BaseAuthMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(AuthMenu record);

    int insertSelective(AuthMenu record);

    AuthMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(AuthMenu record);

    int updateByPrimaryKey(AuthMenu record);
}