package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.entity.AuthRole;
import com.seeyu.fw.auth.mapper.base.BaseAuthRoleMapper;
import com.seeyu.fw.auth.vo.abst.AuthRoleAbstractInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthRoleMapper extends BaseAuthRoleMapper {

    List<AuthRole> selectAccountGrantActiveRoleList(Integer accountId);

    int getRoleNameCount(@Param("roleId")Integer roleId, @Param("roleName")String roleName);

}