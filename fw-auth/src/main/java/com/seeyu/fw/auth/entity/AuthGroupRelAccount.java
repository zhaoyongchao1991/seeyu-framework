package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

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