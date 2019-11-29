package com.seeyu.fw.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@Data
public class AuthAccountAddModel {

    /**
     * 账户
     */
    private String accountAccount;

    /**
     * 密码
     */
    @JsonIgnore
    private String accountPassword;

    /**
     * 激活状态
     */
    private Integer accountActive;
}
