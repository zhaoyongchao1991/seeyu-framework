package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.entity.AuthSystemAccount;
import com.seeyu.fw.auth.mapper.base.BaseAuthSystemAccountMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface AuthSystemAccountMapper extends BaseAuthSystemAccountMapper {

    AuthSystemAccount selectSystemAccountByAccount(String account);

    void updateSystemAccountPassword(@Param("accountId") Integer accountId, @Param("accountPassword") String accountPassword, @Param("accountModifyPwLastTime") Date accountModifyPwLastTime);

}