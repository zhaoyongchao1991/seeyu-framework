package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_menu
 */
@Data
@ToString
public class AuthMenu {
    private Integer menuId;

    /**
     * 父菜单
     */
    private Integer menuPid;

    /**
     * 所属服务
     */
    private Integer menuServiceId;

    /**
     * 资源
     */
    private Integer menuResourceId;

    /**
     * 菜单标识名
     */
    private String menuName;

    /**
     * 菜单名称
     */
    private String menuText;

    private String menuUri;

    /**
     * 图标
     */
    private String menuIcon;

    /**
     * 排序
     */
    private Integer menuOrder;

    /**
     * 备注
     */
    private String menuRemark;

    /**
     * 状态 0:失效 1:激活
     */
    private Integer menuActive;

    private Date menuAddTime;

    private String menuAddUser;

    private Date menuModifyTime;

    private String menuModifyUser;
}