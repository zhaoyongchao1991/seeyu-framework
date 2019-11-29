package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_role
 */
@Data
@ToString
public class AuthRole {
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色显示名称
     */
    private String roleText;

    /**
     * 描述信息
     */
    private String roleRemark;

    /**
     * 状态 0:失效 1:激活
     */
    private Integer roleActive;

    private Date roleAddTime;

    private String roleAddUser;

    private Date roleModifyTime;

    private String roleModifyUser;
}