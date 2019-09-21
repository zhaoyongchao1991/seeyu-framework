package com.seeyu.mvc.security.constant;

public class ResourceLevelConstant {

    /**
     * 需要登录才能访问的资源
     */
    public static final int LEVEL_LOGIN = 2;

    /**
     * 不需要登录就能访问的资源
     */
    public static final int LEVEL_PASS = 1;

    /**
     * 管控的资源，需要分配才能访问
     */
    public static final int LEVEL_CONTROL = 3;

}