package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.mapper.base.BaseAuthRoleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthRoleMapper extends BaseAuthRoleMapper {



    int getRoleNameCount(@Param("roleId")Integer roleId, @Param("roleName")String roleName);



}