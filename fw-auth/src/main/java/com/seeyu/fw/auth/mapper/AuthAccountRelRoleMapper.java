package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.mapper.base.BaseAuthAccountRelRoleMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthAccountRelRoleMapper extends BaseAuthAccountRelRoleMapper {

    @Delete("delete from auth_account_r_role where role_id=#{roleId,jdbcType=INTEGER}")
    void deleteRelationByRole(Integer roleId);

    @Delete("delete from auth_account_r_role where account_id=#{accountId,jdbcType=INTEGER}")
    void deleteRelationByAccount(Integer accountId);

    @Delete("delete from auth_account_r_role where account_id=#{accountId,jdbcType=INTEGER} and role_id=#{roleId,jdbcType=INTEGER}")
    void deleteRelation(@Param("accountId") Integer accountId, @Param("roleId") Integer roleId);

    @ResultType(Integer.class)
    @Select("select count(1) from auth_account_r_role t where t.role_id=#{roleId,jdbcType=INTEGER}")
    int getCountByRoleId(Integer roleId);

}