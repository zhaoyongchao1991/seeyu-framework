package com.seeyu.mvc.common.web.context.user;


import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.mvc.common.model.LoginUserInfoI;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public class LoginUserInfoContext {

    public static boolean isLogin() {
        return RequestLoginInfoContext.isLogin();
    }

    public static LoginUserInfoI getLoginUser() throws LoginAuthenticationFailException {
        return RequestLoginInfoContext.getLoginUser();
    }

    public static <T> T getLoginUser(Class<T> clazz) throws LoginAuthenticationFailException {
        return RequestLoginInfoContext.getLoginUser(clazz);
    }

    public static void putLoginUser(LoginUserInfoI user) {
        if(LoginUserContainerContext.isSessionContainer()){
            SessionLoginInfoContext.putLoginUser(user);
        }
        //else {
            RequestLoginInfoContext.putLoginUser(user);
        //}
    }

}
