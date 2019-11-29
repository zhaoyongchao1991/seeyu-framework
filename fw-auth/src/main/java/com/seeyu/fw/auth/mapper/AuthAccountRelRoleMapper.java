package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.mapper.base.BaseAuthAccountRelRoleMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthAccountRelRoleMapper extends BaseAuthAccountRelRoleMapper {

    @Delete("delete from auth_account_r_role where account_id=#{accountId}")
    void deleteUserRelation(Integer accountId);

}