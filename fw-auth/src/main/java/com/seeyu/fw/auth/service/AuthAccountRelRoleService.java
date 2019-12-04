package com.seeyu.fw.auth.service;

import com.seeyu.fw.auth.entity.AuthAccountRelRole;
import com.seeyu.fw.auth.mapper.AuthAccountRelRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@Service
public class AuthAccountRelRoleService {

    @Autowired
    private AuthAccountRelRoleMapper accountRelRoleMapper;


    @Transactional(rollbackFor = Exception.class)
    void addRelation(Integer accountId, List<Integer> roleIds, String actionUser){
        if(roleIds != null){
            for(Integer roleId : roleIds){
                this.addRelation(accountId, roleId, actionUser);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    void addRelation(Integer accountId, Integer roleId, String actionUser){
        AuthAccountRelRole accountRelRole = new AuthAccountRelRole();
        accountRelRole.setAccountId(accountId);
        accountRelRole.setRoleId(roleId);
        accountRelRole.setModifyUser(actionUser);
        accountRelRole.setModifyTime(new Date());
        accountRelRole.setAddUser(accountRelRole.getModifyUser());
        accountRelRole.setAddTime(accountRelRole.getModifyTime());
        this.accountRelRoleMapper.insert(accountRelRole);
    }

    @Transactional(rollbackFor = Exception.class)
    void deleteRelations(Integer accountId, List<Integer> roleIds){
        if(roleIds != null){
            for(Integer roleId : roleIds){
                this.deleteRelation(accountId, roleId);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    void deleteRelationByAccount(Integer accountId){
        this.accountRelRoleMapper.deleteRelationByAccount(accountId);
    }

    @Transactional(rollbackFor = Exception.class)
    void deleteRelation(Integer accountId, Integer roleId){
        this.accountRelRoleMapper.deleteRelation(accountId, roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    void refreshAccountRoleRelations(Integer accountId, List<Integer> roleIds, String actionUser){
        this.deleteRelationByAccount(accountId);
        this.addRelation(accountId, roleIds, actionUser);
    }

}
