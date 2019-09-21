package com.seeyu.mvc.common.model;

import java.io.Serializable;

/**
 * @author seeyu
 * @date 2019/7/2
 */
public interface LoginUserInfoI<T> {

    Serializable getId();

    String getName();

    T getInfo();

}
