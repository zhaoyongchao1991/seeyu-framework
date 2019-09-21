package com.seeyu.mvc.security.filter;

import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.mvc.common.web.context.user.LoginUserContainerContext;
import com.seeyu.mvc.common.web.context.user.RequestLoginInfoContext;
import com.seeyu.mvc.common.web.context.user.SessionLoginInfoContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 默认是在session中抽取登录用户数据
 * @author seeyu
 * @date 2019/7/2
 */
@Component
public class SessionLoginUserFetchFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.selectLoginUserContainer();
        this.putSessionLoginUser2Request();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void selectLoginUserContainer(){
        LoginUserContainerContext.selectSessionContainer();
    }

    private void putSessionLoginUser2Request(){
        try {
            if (SessionLoginInfoContext.isLogin()) {
                RequestLoginInfoContext.putLoginUser(SessionLoginInfoContext.getLoginUser());
            }
        }
        catch (LoginAuthenticationFailException e){
            throw new RuntimeException("获取登录用户信息失败", e);
        }

    }

}
