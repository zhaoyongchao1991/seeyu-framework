package com.seeyu.fw.auth.service;

import com.seeyu.fw.auth.entity.AuthSystemAccount;
import com.seeyu.fw.auth.mapper.AuthSystemAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@Service
public class AuthSystemAccountService {

    @Autowired
    private AuthSystemAccountMapper systemAccountMapper;

    public AuthSystemAccount getSystemAccountById(Integer accountId){
        return this.systemAccountMapper.selectByPrimaryKey(accountId);
    }

    public AuthSystemAccount getSystemAccountByAccount(String account){
        return this.systemAccountMapper.selectSystemAccountByAccount(account);
    }

    void modifySystemAccountPassword(Integer accountId, String password){
        this.systemAccountMapper.updateSystemAccountPassword(accountId, password, new Date());
    }

    void resetSystemAccountPassword(Integer accountId, String password){
        this.systemAccountMapper.updateSystemAccountPassword(accountId, password, null);
    }

}
