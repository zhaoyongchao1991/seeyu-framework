package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_resource
 */
@Data
@ToString
public class AuthResource {
    private Integer resourceId;

    /**
     * 所属服务
     */
    private Integer resourceServiceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源显示名称
     */
    private String resourceText;

    /**
     * 备注
     */
    private String resourceRemark;

    /**
     * 资源地址
     */
    private String resourceUri;

    /**
     * 等级:2:登录才可以访问; 1:不登录可以访问; 3:授权访问;
     */
    private Integer resourceLevel;

    /**
     * 状态 0:失效 1:激活
     */
    private Integer resourceActive;

    private Date resourceAddTime;

    private String resourceAddUser;

    private Date resourceModifyTime;

    private String resourceModifyUser;
}