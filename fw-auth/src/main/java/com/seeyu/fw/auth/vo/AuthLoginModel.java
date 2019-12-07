package com.seeyu.fw.auth.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author seeyu
 * @date 2019/12/4
 */
@Data
public class AuthLoginModel {

    private String account;
    private String password;
    private Map<String,Object> info;

}
