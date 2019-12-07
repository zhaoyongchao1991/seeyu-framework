package com.seeyu.fw.auth.service;

import com.seeyu.core.utils.Assert;
import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.exception.AccountOrPasswordNotMatchException;
import com.seeyu.fw.auth.mapper.AuthAccountMapper;
import com.seeyu.fw.auth.utils.PasswordTool;
import com.seeyu.fw.auth.vo.AuthLoginModel;
import com.seeyu.fw.auth.vo.AuthLoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seeyu
 * @date 2019/12/4
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthAccountMapper accountMapper;
    @Autowired
    private AuthJwtService jwtService;

    public AuthLoginUserInfo authentication(AuthLoginModel loginModel) throws AccountOrPasswordNotMatchException {
        Assert.notNull(loginModel.getAccount(), "Account不能为空");
        Assert.notNull(loginModel.getPassword(), "Password不能为空");

        AuthAccount theAccount = this.accountMapper.selectAccountByAccount(loginModel.getAccount());
        boolean isSystemAdmin = false;
        if(theAccount  == null){
            isSystemAdmin = true;
            theAccount = this.accountMapper.selectSystemAccountByAccount(loginModel.getAccount());
        }
        //检查密码匹配
        if(theAccount == null || !PasswordTool.passwordEquals(theAccount.getAccountPassword(), loginModel.getPassword())){
            throw new AccountOrPasswordNotMatchException();
        }

        AuthLoginUserInfo userInfo = this.wrapLoginUser(theAccount, isSystemAdmin);
        userInfo.setToken(this.createToken(userInfo, loginModel.getInfo()));
        return userInfo;
    }


    private AuthLoginUserInfo wrapLoginUser(AuthAccount authAccount, boolean systemAdmin){
        AuthLoginUserInfo loginInfo = AuthLoginUserInfo.wrap(authAccount, systemAdmin);
        return loginInfo;
    }

    private String createToken(AuthLoginUserInfo user, Map<String, Object> info) {
        return jwtService.createToken(warpLoginInfo(user, info));
    }

    private Map<String,Object> warpLoginInfo(AuthLoginUserInfo user, Map<String, Object> info){

        Map<String,Object> map = new HashMap<>();

        map.put("accountId", user.getAccountId());
        map.put("account", user.getAccount());
        map.put("active", user.getActive());
        map.put("modifyPwLastTime", user.getModifyPwLastTime());
        if(info != null){
            map.putAll(info);
        }
        return map;
    }
}
