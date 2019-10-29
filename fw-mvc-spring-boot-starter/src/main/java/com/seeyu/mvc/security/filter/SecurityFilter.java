package com.seeyu.mvc.security.filter;

import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.core.utils.BaseJsonData;
import com.seeyu.mvc.common.constant.ResponseCode;
import com.seeyu.mvc.common.model.LoginUserInfoI;
import com.seeyu.mvc.common.model.SecurityResourceHolderI;
import com.seeyu.mvc.common.model.SecurityUserHolderI;
import com.seeyu.mvc.common.service.SecurityPolicyServiceI;
import com.seeyu.mvc.common.web.context.user.LoginUserInfoContext;
import com.seeyu.mvc.constant.I18nMessageKeyConstant;
import com.seeyu.mvc.security.filter.helper.SecurityResourceCheckHelper;
import com.seeyu.mvc.utils.ResponseUtils;
import com.seeyu.mvc.web.filter.helper.RequestUriHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/2/12
 */
@Slf4j
@Component
public class SecurityFilter implements Filter {

    private List<SecurityResourceHolderI> securityResourcesList;
    @Autowired
    private SecurityPolicyServiceI securityPolicyService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.reLoadResources();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        String uri = getRequestUri(req);
        log.debug("访问uri: " + uri);

        BaseJsonData jsonData = permissionCheck(req, resp);
        if(! jsonData.isSuccess()){
            ResponseUtils.responseJsonData(resp, jsonData);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public synchronized void reLoadResources(){
        this.securityResourcesList = this.securityPolicyService.getResources();
    }

    /**
     * 权限验证
     * @param req
     * @param resp
     * @return
     */
    private BaseJsonData permissionCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = getRequestUri(req);
        SecurityResourceCheckHelper securityResourceCheckHelper = new SecurityResourceCheckHelper(this.securityResourcesList, uri);

        //禁用资源不能访问
        if(securityResourceCheckHelper.isDisableResource()){
            log.debug("资源被禁用,不能访问: " + uri);
            return BaseJsonData.ERROR().setCode(ResponseCode.AUTHORITY_AUTHENTICATION_FAIL).setMessage(I18nMessageKeyConstant.SERVER_SECURITY_ALERT_DISABLE_RESOURCE);
        }
        //必须要显示分配 才能放访问的资源
        if(securityResourceCheckHelper.isLevel3()){
            if(checkLoginUserResource(req, uri)){
                return BaseJsonData.SUCCESS();
            }
            else{
                log.debug("需要分配权限才能访问: " + uri);
                return BaseJsonData.ERROR().setCode(ResponseCode.AUTHORITY_AUTHENTICATION_FAIL).setMessage(I18nMessageKeyConstant.SERVER_SECURITY_ALERT_AUTHENTICATION_FAILED);
            }
        }
        //不需要登陆就可以访问的资源
        else if(securityResourceCheckHelper.isLevel1()){
            log.debug("不需要登录就能访问: " + uri);
            return BaseJsonData.SUCCESS();
        }
        //必须登录才可以访问的资源(默认)
        else{
            if(isLogin(req)){
                return BaseJsonData.SUCCESS();
            }
            else{
                log.debug("需要登录才能访问: " + uri);
                return BaseJsonData.ERROR().setCode(ResponseCode.LOGIN_AUTHENTICATION_FAIL).setMessage(I18nMessageKeyConstant.SERVER_SECURITY_ALERT_LOGIN_AUTHENTICATION_FAILED);
            }
        }
    }

    private boolean isLogin(HttpServletRequest req) {
       return LoginUserInfoContext.isLogin();
    }

    /**
     * 检查登录用户的资源
     * @param req
     * @return
     */
    private boolean checkLoginUserResource(HttpServletRequest req, String uri) {
        try {
            LoginUserInfoI loginUserInfo = LoginUserInfoContext.getLoginUser();
            SecurityUserHolderI me = loginUserInfo == null ? null : (SecurityUserHolderI)loginUserInfo.getInfo();
            if(me == null){
                return false;
            }
            if(me.isAdmin()){
                return true;
            }
            List<SecurityResourceHolderI> userResource = me.getResources();
            if(userResource != null){
                for(SecurityResourceHolderI re : me.getResources()){
                    if(uri.equals(re.getResourceUri())){
                        return true;
                    }
                }
            }
            return false;
        }catch (LoginAuthenticationFailException e){
            return false;
        }
    }

    private String getRequestUri(HttpServletRequest req){
        return RequestUriHelper.getBaseRequestUri(req);
    }

}
