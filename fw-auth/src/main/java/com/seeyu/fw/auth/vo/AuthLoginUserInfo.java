package com.seeyu.fw.auth.vo;

import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.entity.AuthResource;
import com.seeyu.fw.auth.entity.AuthRole;
import com.seeyu.fw.auth.vo.abst.AuthRoleAbstractInfo;
import com.seeyu.fw.auth.vo.authority.MenuPlain;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;


/**
 * @author seeyu
 * @date 2019/4/30
 */
@Data
@ToString
public class AuthLoginUserInfo {

    private Integer accountId;

    /**
     * 账户
     */
    private String account;

    /**
     * 激活状态
     */
    private Integer active;

    /**
     * 最后修改密码时间
     */
    private Date modifyPwLastTime;

    private boolean systemAdmin;

    /**
     * 密码是否过期
     */
    private boolean passwordExpired;

    private String token;
    private List<AuthRoleAbstractInfo> roles;
    private List<AuthResource> resources;
    private List<MenuPlain> menus;

    public static AuthLoginUserInfo wrap(AuthAccount account, boolean systemAdmin){
        AuthLoginUserInfo user = new AuthLoginUserInfo();
        user.setSystemAdmin(systemAdmin);
        user.setAccountId(account.getAccountId());
        user.setAccount(account.getAccountAccount());
        user.setActive(account.getAccountActive());
        user.setModifyPwLastTime(account.getAccountModifyPwLastTime());
        return user;
    }

}
