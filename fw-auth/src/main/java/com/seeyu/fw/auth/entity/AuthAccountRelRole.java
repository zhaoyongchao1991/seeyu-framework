package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_account_r_role
 */
@Data
@ToString
public class AuthAccountRelRole {
    private Integer id;

    private Integer roleId;

    private Integer accountId;

    private Date addTime;

    private String addUser;

    private Date modifyTime;

    private String modifyUser;
}