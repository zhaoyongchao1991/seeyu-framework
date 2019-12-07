package com.seeyu.fw.auth.service;

import com.seeyu.fw.auth.entity.AuthSystemAccount;
import com.seeyu.fw.auth.exception.PasswordNotMatchException;
import com.seeyu.fw.auth.mapper.AuthSystemAccountMapper;
import com.seeyu.fw.auth.utils.PasswordTool;
import com.seeyu.fw.auth.vo.AuthModifyPassWordModel;
import com.seeyu.normal.utils.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author seeyu
 * @date 2019/12/5
 */
@Service
public class AuthSystemAccountService {

    @Autowired
    private AuthSystemAccountMapper systemAccountMapper;

    /**
     * @param accountId 账号id
     * @param newPassword 明文密码
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetAccountPassword(Integer accountId, String newPassword, String actionUser) {
        this.resetAccountPassword(accountId, newPassword);
    }

    /**
     * @param model password 旧密码，newPassword新密码明文
     * @param actionUser
     * @throws PasswordNotMatchException
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifyAccountPassword(AuthModifyPassWordModel model, String actionUser) throws PasswordNotMatchException {
        this.modifyAccountPassword(model.getAccountId(), model.getPassword(), model.getNewPassword());
    }


    private void modifyAccountPassword(Integer accountId, String oldPassword, String newPassword) throws PasswordNotMatchException {
        //FormValidator.password("密码", newPassword);
        AuthSystemAccount account = this.systemAccountMapper.selectByPrimaryKey(accountId);
        if(account != null){
            //检查旧密码是否匹配
            if(!PasswordTool.passwordEquals(account.getAccountPassword(), oldPassword)){
                throw new PasswordNotMatchException();
            }
            this.systemAccountMapper.updateSystemAccountPassword(accountId, PasswordTool.passwordPlaintextEncrypt(newPassword), new Date());
        }
    }

    private void resetAccountPassword(Integer accountId, String newPassword){
        String pw = PasswordTool.passwordPlaintextEncrypt(newPassword);
        this.systemAccountMapper.updateSystemAccountPassword(accountId, pw, null);
    }

}
