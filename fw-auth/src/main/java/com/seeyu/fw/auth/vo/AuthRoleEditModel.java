package com.seeyu.fw.auth.vo;

import lombok.Data;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@Data
public class AuthRoleEditModel {

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

}
