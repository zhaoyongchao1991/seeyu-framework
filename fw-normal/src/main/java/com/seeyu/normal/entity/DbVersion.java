package com.seeyu.normal.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author seeyu
 * @date 2019/6/10
 */
@Data
public class DbVersion {
    private String version;
    private String target;
    private Date time;
}