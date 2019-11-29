package com.seeyu.fw.auth.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * auth_group_r_account
 */
@Data
@ToString
public class AuthGroupRelAccount {
    private Integer id;

    private Integer groupId;

    private Integer accountId;

    private Date addTime;

    private String addUser;

    private Date modifyTime;

    private String modifyUser;
}