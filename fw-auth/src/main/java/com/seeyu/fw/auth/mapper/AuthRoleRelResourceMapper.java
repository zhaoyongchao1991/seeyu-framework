package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.mapper.base.BaseAuthRoleRelResourceMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface AuthRoleRelResourceMapper extends BaseAuthRoleRelResourceMapper {

    @Delete("delete from auth_role_r_resource where role_id=#{roleId,jdbcType=INTEGER}")
    int deleteRelationByRole(Integer roleId);

    @Delete("delete from auth_role_r_resource where role_id=#{roleId,jdbcType=INTEGER} and resource_id=#{resourceId,jdbcType=INTEGER}")
    int deleteRelation(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId);

}