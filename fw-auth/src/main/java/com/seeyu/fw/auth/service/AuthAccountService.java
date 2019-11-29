package com.seeyu.fw.auth.service;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.fw.auth.config.AuthConfig;
import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.exception.AccountNotExistException;
import com.seeyu.fw.auth.mapper.AuthAccountMapper;
import com.seeyu.fw.auth.service.helper.AccountServiceHelper;
import com.seeyu.fw.auth.vo.AuthAccountAddModel;
import com.seeyu.fw.auth.vo.AuthModifyPassWordModel;
import com.seeyu.normal.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;


/**
 * @author seeyu
 * @date 2019/4/11
 */
@Service
public class AuthAccountService {

    @Autowired
    private AuthAccountMapper accountMapper;
    @Autowired
    private AccountServiceHelper accountServiceHelper;
    @Autowired
    private AuthAccountRelRoleService accountRelRoleService;
    @Autowired
    private AuthSystemAccountService systemAccountService;

    @Transactional(rollbackFor = Exception.class)
    public void refreshAccountRoles(Integer accountId, Integer[] roleIds, String actionUser){
        this.accountRelRoleService.deleteAccountRelation(accountId);
        this.addAccountRoleRelations(accountId, roleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editUserRoles(Integer accountId, Integer[] addRoleIds, Integer[] removeRoleIds, String actionUser){
        this.addAccountRoleRelations(accountId, addRoleIds);
        this.removeAccountRoleRelations(accountId, removeRoleIds);
    }


    /**
     * @param account 账号
     * @param newPassword 明文密码
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetAccountPassword(String account, String newPassword, String actionUser) throws AccountNotExistException {
        this.accountServiceHelper.updatePassword(account, Md5Utils.encrypt(Md5Utils.encrypt(newPassword), AuthConfig.PASSWORD_SALT), true);
    }



//    @Transactional(rollbackFor = Exception.class)
//    public void modifyPassword(AuthModifyPassWordModel modifyPassWord, String actionUser) throws Exception {
//        this.accountServiceHelper.modifyPassword(modifyPassWord);
//    }


    @Transactional(rollbackFor = Exception.class)
    public void enable(Integer accountId, String actionUser){
        AuthAccount account = new AuthAccount();
        account.setAccountId(accountId);
        account.setAccountActive(ActivationState.getActiveState());
        account.setAccountModifyTime(new Date());
        account.setAccountModifyUser(actionUser);
        this.accountMapper.updateByPrimaryKeySelective(account);
    }


    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer accountId, String actionUser){
        AuthAccount account = new AuthAccount();
        account.setAccountId(accountId);
        account.setAccountActive(ActivationState.getDisableState());
        account.setAccountModifyTime(new Date());
        account.setAccountModifyUser(actionUser);
    }


//    public UserInfo authentication(AuthAccount account) throws Exception {
//        return accountServiceHelper.authentication(account);
//    }


    @Transactional(rollbackFor = Exception.class)
    public AuthAccount addAccount(AuthAccountAddModel model, String actionUser) {
        AuthAccount account = this.accountServiceHelper.wrapAddAccount(model);
        this.accountServiceHelper.validateAccount(account);

        account.setAccountAddUser(actionUser);
        account.setAccountModifyUser(actionUser);
        account.setAccountAddTime(new Date());
        account.setAccountModifyTime(account.getAccountAddTime());
        this.accountMapper.insert(account);
        return account;
    }


//    @Transactional(rollbackFor = Exception.class)
//    public Serializable saveAccountAndRoles(@RequestBody AccountRolesTo accountRoles) throws Exception {
//        Serializable actId = this.register(accountServiceHelper.cast2RegisterAccount(accountRoles));
//        this.refreshUserRoles((Integer) actId, accountRoles.getAddRoleIds());
//        return actId;
//    }


//    @Transactional(rollbackFor = Exception.class)
//    public void editAccount(AccountTo editAccount) {
//        Account account = accountServiceHelper.wrapEditAccount(editAccount);
//        accountServiceHelper.validateAccount(account);
//        accountServiceHelper.fillAccount(account);
//        accountMapper.updateByPrimaryKey(account);
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public void editAccountAndRoles(AccountRolesTo accountRoles) throws Exception {
//        this.editAccount(accountRoles);
//        this.refreshUserRoles(accountRoles.getActId(), accountRoles.getAddRoleIds());
//    }


    private void addAccountRoleRelations(Integer actId, Integer[] addRoleIds){
//        if(addRoleIds != null){
//            for (Integer roleId : addRoleIds){
//                UserRole userRole = new UserRole();
//                userRole.setUreUsrid(actId);
//                userRole.setUreRoleid(roleId);
//                userRoleService.addUserRole(userRole);
//            }
//        }
    }


    private void removeAccountRoleRelations(Integer actId, Integer[] removeRoleIds){
//        if(removeRoleIds != null){
//            for (Integer roleId : removeRoleIds){
//                UserRole userRole = new UserRole();
//                userRole.setUreUsrid(actId);
//                userRole.setUreRoleid(roleId);
//                userRoleService.deleteUserRole(userRole);
//            }
//        }
    }


}
