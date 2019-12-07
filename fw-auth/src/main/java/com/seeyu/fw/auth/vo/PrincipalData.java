package com.seeyu.fw.auth.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author seeyu
 * @date 2019/4/16
 */
@Data
@ToString
public class PrincipalData<T> {

    private boolean isExpired;

    private String subject;

    private T principal;

    private String refreshToken;

}
