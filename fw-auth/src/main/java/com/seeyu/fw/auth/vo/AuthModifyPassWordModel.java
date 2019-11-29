package com.seeyu.fw.auth.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author seeyu
 * @date 2019/5/6
 */
@Data
@ToString
public class AuthModifyPassWordModel {

    private Integer accountId;
    private String password;
    private String newPassword;

    public AuthModifyPassWordModel(Integer accountId, String password, String newPassword){
        this.accountId = accountId;
        this.password = password;
        this.newPassword = newPassword;
    }

}