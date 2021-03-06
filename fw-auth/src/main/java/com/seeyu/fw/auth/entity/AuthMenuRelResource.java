package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_menu_r_resource
 */
@Data
@ToString
public class AuthMenuRelResource {
    private Integer id;

    private Integer menuId;

    private Integer resourceId;

    private Date addTime;

    private String addUser;

    private Date modifyTime;

    private String modifyUser;
}