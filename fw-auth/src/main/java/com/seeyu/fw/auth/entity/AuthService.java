package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_service
 */
@Data
@ToString
public class AuthService {
    private Integer serviceId;

    /**
     * 服务名称
     */
    private String serviceName;

    private String serviceText;

    /**
     * 是否是系统服务
     */
    private Boolean serviceSystem;

    /**
     * 信息
     */
    private String serviceInfo;

    /**
     * 激活状态
     */
    private Boolean serviceActive;

    private Date serviceAddTime;

    private String serviceAddUser;

    private Date serviceModifyTime;

    private String serviceModifyUser;
}