package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_group_r_role
 */
@Data
@ToString
public class AuthGroupRelRole {
    private Integer id;

    private Integer groupId;

    private Integer roleId;

    private Date addTime;

    private String addUser;

    private Date modifyTime;

    private String modifyUser;
}