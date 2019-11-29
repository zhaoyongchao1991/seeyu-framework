package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_role_r_resource
 */
@Data
@ToString
public class AuthRoleRelResource {
    private Integer id;

    /**
     * 角色
     */
    private Integer roleId;

    /**
     * 资源
     */
    private Integer resourceId;

    private Date addTime;

    private String addUser;

    private Date modifyTime;

    private String modifyUser;
}