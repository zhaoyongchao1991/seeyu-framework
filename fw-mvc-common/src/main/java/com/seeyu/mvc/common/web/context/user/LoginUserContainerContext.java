package com.seeyu.mvc.common.web.context.user;

import com.seeyu.mvc.common.web.context.ServletContext;

/**
 * 用户存储容器， 分request和session 两种
 * @author seeyu
 * @date 2019/7/2
 */
public class LoginUserContainerContext {


    public static final String LOGIN_USER_CONTAINER_KEY = LoginUserContainerContext.class.getName() + "LOGIN_USER_CONTAINER_KEY";

    private final static String IN_SESSION = "IN_SESSION";
    private final static String IN_REQUEST = "IN_REQUEST";


    public static boolean isSessionContainer(){
        return IN_SESSION.equals(getContainerType());
    }


    public static void selectSessionContainer(){
        putContainerType(IN_SESSION);
    }

    private static String getContainerType(){
        String s = ServletContext.getAttribute(String.class, LOGIN_USER_CONTAINER_KEY, ServletContext.request_scope);
        assert s != null;
        return s;
    }

    private static void putContainerType(String type){
        assert type != null;
        ServletContext.setAttribute(LOGIN_USER_CONTAINER_KEY, type, ServletContext.request_scope);
    }

}
