package com.seeyu.fw.auth.service;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.exception.AccountAlreadyExistException;
import com.seeyu.fw.auth.exception.AccountNotExistException;
import com.seeyu.fw.auth.exception.PasswordNotMatchException;
import com.seeyu.fw.auth.mapper.AuthAccountMapper;
import com.seeyu.fw.auth.service.helper.AuthAccountServiceHelper;
import com.seeyu.fw.auth.utils.PasswordTool;
import com.seeyu.fw.auth.vo.AuthAccountAddModel;
import com.seeyu.fw.auth.vo.AuthModifyPassWordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author seeyu
 * @date 2019/4/11
 */
@Service
public class AuthAccountService {

    @Autowired
    private AuthAccountMapper accountMapper;
    @Autowired
    private AuthAccountServiceHelper accountServiceHelper;
    @Autowired
    private AuthAccountRelRoleService accountRelRoleService;
    @Autowired
    private AuthSystemAccountService systemAccountService;


    @Transactional(rollbackFor = Exception.class)
    public void deleteAccounts(List<Integer> accountIds){
        if(accountIds != null){
            for(Integer id : accountIds){
                this.deleteAccount(id);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAccount(Integer accountId){
        this.accountRelRoleService.deleteRelationByAccount(accountId);
        this.accountMapper.deleteByPrimaryKey(accountId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void refreshAccountRelRoleRelations(Integer accountId, List<Integer> roleIds, String actionUser){
        this.accountRelRoleService.refreshAccountRoleRelations(accountId, roleIds, actionUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editAccountRelRoleRelations(Integer accountId, List<Integer> addRoleIds, List<Integer> removeRoleIds, String actionUser){
        this.accountRelRoleService.addRelation(accountId, addRoleIds, actionUser);
        this.accountRelRoleService.deleteRelations(accountId, removeRoleIds);
    }


    /**
     * @param accountId 账号id
     * @param newPassword 明文密码
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetAccountPassword(Integer accountId, String newPassword, String actionUser) {
        this.accountServiceHelper.resetAccountPassword(accountId, newPassword);
    }

    /**
     * @param model password 旧密码，newPassword新密码明文
     * @param actionUser
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifyAccountPassword(AuthModifyPassWordModel model, String actionUser) throws PasswordNotMatchException {
        this.accountServiceHelper.modifyAccountPassword(model.getAccountId(), model.getPassword(), model.getNewPassword());
    }

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

    @Transactional(rollbackFor = Exception.class)
    public AuthAccount addAccount(AuthAccountAddModel model, String actionUser) throws AccountAlreadyExistException {
        AuthAccount account = new AuthAccount();
        account.setAccountAccount(model.getAccountAccount());
        account.setAccountPassword(model.getAccountPassword());
        account.setAccountActive(model.getAccountActive());
        account.setAccountAddUser(actionUser);
        account.setAccountModifyUser(actionUser);
        account.setAccountAddTime(new Date());
        account.setAccountModifyTime(account.getAccountAddTime());
        this.accountServiceHelper.validateAccount(account);
        account.setAccountPassword(PasswordTool.passwordPlaintextEncrypt(account.getAccountPassword()));
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





}
