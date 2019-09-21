package com.seeyu.mvc.common.web.context.user;


import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.mvc.common.model.LoginUserInfoI;
import com.seeyu.mvc.common.web.context.ServletContext;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public class RequestLoginInfoContext {

    private static final String LOGIN_USER_KEY_IN_REQUEST = RequestLoginInfoContext.class.getName() + "LOGIN_USER_KEY_IN_REQUEST";

    public static boolean isLogin(){
        return ServletContext.getAttribute(LOGIN_USER_KEY_IN_REQUEST, ServletContext.request_scope) != null;
    }

    public static LoginUserInfoI getLoginUser() throws LoginAuthenticationFailException {
        LoginUserInfoI user = ServletContext.getAttribute(LoginUserInfoI.class, LOGIN_USER_KEY_IN_REQUEST, ServletContext.request_scope);
        if(user == null || user.getInfo() == null){
            throw new LoginAuthenticationFailException();
        }
        return user;
    }

    public static <T> T getLoginUser(Class<T> clazz) throws LoginAuthenticationFailException {
        return (T)getLoginUser().getInfo();
    }

    public static void putLoginUser(LoginUserInfoI user) {
        assert user != null && user.getInfo() != null;
        ServletContext.setAttribute(LOGIN_USER_KEY_IN_REQUEST, user, ServletContext.request_scope);
    }

}
