package com.seeyu.mvc.audit.log.filter;

import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.mvc.audit.log.AuditLogger;
import com.seeyu.mvc.audit.log.interceptor.helper.AuditLogInfoParseHelper;
import com.seeyu.mvc.common.model.AuditLogHolderI;
import com.seeyu.mvc.common.model.LoginUserInfoI;
import com.seeyu.mvc.common.service.AuditLogServiceI;
import com.seeyu.mvc.common.web.context.ServletContext;
import com.seeyu.mvc.common.web.context.user.LoginUserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author seeyu
 * @date 2019/6/28
 */
@Slf4j
@Component
public class AuditLogFilter implements Filter {

    @Autowired
    private AuditLogServiceI auditLogService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{

            this.auditLoggingStart();

            filterChain.doFilter(servletRequest, servletResponse);
            this.auditLoggingEnd();
            this.processAuditLog();
        }
        finally {
            AuditLogger.clear();
        }
    }

    @Override
    public void destroy() {

    }

    private void processAuditLog() {
        try {
            if (!AuditLogger.isEnable()) {
                return;
            }
            AuditLogHolderI auditLog = AuditLogger.getAuditLog();
            if (auditLog == null) {
                return;
            }
            AuditLogger.setInfo(new AuditLogInfoParseHelper(auditLog).parse());
            this.auditLogService.saveAuditLog(AuditLogger.getAuditLog());
        } catch (Exception e) {
            log.error("处理审计日志出错", e);
        }
    }

    private void auditLoggingStart(){
        AuditLogger.init();
    }

    private void auditLoggingEnd() {
        try{
            AuditLogger.setResponseTime(new Date());
            if(LoginUserInfoContext.isLogin()){
                LoginUserInfoI loginUser = LoginUserInfoContext.getLoginUser();
                AuditLogger.setOperateUserId(loginUser.getId());
                AuditLogger.setOperateUserName(loginUser.getName());
            }
        }
        catch (LoginAuthenticationFailException e){
            log.error("获取用户数据失败", e);
            throw new RuntimeException(e);
        }
    }

}
