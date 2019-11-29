package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_system_account
 */
@Data
@ToString
public class AuthSystemAccount {
    private Integer accountId;

    /**
     * 账户
     */
    private String accountAccount;

    /**
     * 密码
     */
    private String accountPassword;

    /**
     * 激活状态
     */
    private Integer accountActive;

    /**
     * 最后修改密码时间
     */
    private Date accountModifyPwLastTime;

    private Date accountAddTime;

    private String accountAddUser;

    private Date accountModifyTime;

    private String accountModifyUser;
}