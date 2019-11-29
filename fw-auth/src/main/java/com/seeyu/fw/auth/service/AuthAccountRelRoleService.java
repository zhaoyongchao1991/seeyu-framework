package com.seeyu.fw.auth.service;

import com.seeyu.fw.auth.mapper.AuthAccountRelRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@Service
public class AuthAccountRelRoleService {

    @Autowired
    private AuthAccountRelRoleMapper accountRelRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    public void deleteAccountRelation(Integer accountId){
        this.accountRelRoleMapper.deleteUserRelation(accountId);
    }

}
