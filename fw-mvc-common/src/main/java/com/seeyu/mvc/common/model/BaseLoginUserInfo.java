package com.seeyu.mvc.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author seeyu
 * @date 2019/7/2
 */
@Data
public class BaseLoginUserInfo<T> implements LoginUserInfoI{

    private Serializable id;
    private String name;
    private String account;
    private T info;

    public BaseLoginUserInfo(Serializable id,String account, String name, T info){
        this.id = id;
        this.name = name;
        this.info = info;
        this.account = account;
    }


}
