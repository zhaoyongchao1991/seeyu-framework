package com.seeyu.fw.auth.service;

import com.seeyu.core.utils.Assert;
import com.seeyu.fw.auth.constant.GlobalConstant;
import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.entity.AuthResource;
import com.seeyu.fw.auth.exception.AccountOrPasswordNotMatchException;
import com.seeyu.fw.auth.mapper.AuthAccountMapper;
import com.seeyu.fw.auth.service.helper.MenuTreeHelper;
import com.seeyu.fw.auth.utils.PasswordTool;
import com.seeyu.fw.auth.vo.AuthLoginModel;
import com.seeyu.fw.auth.vo.AuthLoginUserInfo;
import com.seeyu.fw.auth.vo.abst.AuthRoleAbstractInfo;
import com.seeyu.fw.auth.vo.authority.MenuPlain;
import com.seeyu.fw.auth.vo.authority.MenuResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author seeyu
 * @date 2019/12/4
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthMenuService menuService;
    @Autowired
    private AuthResourceService resourceService;
    @Autowired
    private AuthRoleService roleService;
    @Autowired
    private AuthAccountMapper accountMapper;
    @Autowired
    private AuthJwtService jwtService;


    public AuthLoginUserInfo authentication(AuthLoginModel loginModel) throws AccountOrPasswordNotMatchException {
        return this.authentication(GlobalConstant.DEFAULT_SERVICE_ID, loginModel);
    }

    public AuthLoginUserInfo authentication(Integer serviceId, AuthLoginModel loginModel) throws AccountOrPasswordNotMatchException {
        Assert.notNull(loginModel.getAccount(), "Account不能为空");
        Assert.notNull(loginModel.getPassword(), "Password不能为空");

        AuthAccount theAccount = this.accountMapper.selectAccountByAccount(loginModel.getAccount());
        boolean isSystemAdmin = false;
        if (theAccount == null) {
            isSystemAdmin = true;
            theAccount = this.accountMapper.selectSystemAccountByAccount(loginModel.getAccount());
        }
        //检查密码匹配
        if (theAccount == null || !PasswordTool.passwordEquals(theAccount.getAccountPassword(), loginModel.getPassword())) {
            throw new AccountOrPasswordNotMatchException();
        }

        AuthLoginUserInfo userInfo = this.wrapLoginUser(serviceId, theAccount, isSystemAdmin);
        userInfo.setToken(this.createToken(userInfo, loginModel.getInfo()));
        return userInfo;
    }

    private AuthLoginUserInfo wrapLoginUser(Integer serviceId, AuthAccount authAccount, boolean systemAdmin) {
        AuthLoginUserInfo loginInfo = AuthLoginUserInfo.wrap(authAccount, systemAdmin);
        if (systemAdmin) {
            loginInfo.setSystemAdmin(true);
            loginInfo.setMenus(this.getAdminPlainMenus(serviceId));
            loginInfo.setMenus(getAdminPlainMenus(serviceId));
        } else {
            loginInfo.setSystemAdmin(false);
            loginInfo.setRoles(AuthRoleAbstractInfo.cast(this.roleService.getAccountGrantActiveRoles(authAccount.getAccountId())));
            //获取菜单
            loginInfo.setMenus(this.getAccountPlainMenus(serviceId, authAccount.getAccountId()));
            //获取用户资源
            //userInfo.setResources(getUserActiveResources(account.getActId()));
        }
        return loginInfo;
    }

    private String createToken(AuthLoginUserInfo user, Map<String, Object> info) {
        return jwtService.createToken(warpLoginInfo(user, info));
    }

    private Map<String, Object> warpLoginInfo(AuthLoginUserInfo user, Map<String, Object> info) {
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", user.getAccountId());
        map.put("account", user.getAccount());
        map.put("active", user.getActive());
        map.put("modifyPwLastTime", user.getModifyPwLastTime());
        if (info != null) {
            map.putAll(info);
        }
        return map;
    }

    private List<MenuPlain> getAdminPlainMenus(Integer serviceId) {
        List<MenuResource> allMenus = this.menuService.getServiceActiveMenus(serviceId);
        List<AuthResource> adminResources = this.resourceService.getServiceActiveResources(serviceId);
        return new MenuTreeHelper(allMenus, adminResources).getUserPlainMenus();
    }

    private List<MenuPlain> getAccountPlainMenus(Integer serviceId, Integer accountId) {
        List<MenuResource> allMenus = this.menuService.getServiceActiveMenus(serviceId);
        List<AuthResource> userResources = this.resourceService.getAccountGrantServiceActiveResources(serviceId, accountId);
        return new MenuTreeHelper(allMenus, userResources).getUserPlainMenus();
    }


}
