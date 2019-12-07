package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.mapper.base.BaseAuthAccountMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface AuthAccountMapper extends BaseAuthAccountMapper {

    AuthAccount selectAccountByAccount(String account);

    AuthAccount selectSystemAccountByAccount(String account);

    void updateAccountPassword(@Param("accountId") Integer accountId, @Param("accountPassword") String accountPassword, @Param("accountModifyPwLastTime") Date accountModifyPwLastTime);

    @ResultType(Integer.class)
    @Select("select count(1) count from(\n" +
            "select 1 from auth_account where account_account=#{account}\n" +
            "union all select 1 from auth_system_account where account_account=#{account})t")
    int getNormalOrSystemAccountCount(String account);

}