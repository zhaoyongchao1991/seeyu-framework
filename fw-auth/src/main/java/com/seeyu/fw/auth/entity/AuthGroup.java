package com.seeyu.fw.auth.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * auth_group
 */
@Data
@ToString
public class AuthGroup {
    private Integer groupId;

    private String groupName;

    private String groupCode;

    private String groupRemark;

    private Date groupAddTime;

    private String groupAddUser;

    private Date groupModifyTime;

    private String groupModifyUser;
}