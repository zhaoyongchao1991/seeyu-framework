package com.seeyu.mvc.audit.log.interceptor;

import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.lang.utils.StringUtils;
import com.seeyu.mvc.audit.log.AuditLogger;
import com.seeyu.mvc.audit.log.annotation.AuditLog;
import com.seeyu.mvc.audit.log.annotation.AuditModule;
import com.seeyu.mvc.audit.log.interceptor.helper.AuditLogOperateTypeParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author seeyu
 * @date 2019/4/2
 */
@Slf4j
@Component
public class AuditLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(this.isAuditLogging(handler)){
            this.initAuditLogInfo(request, (HandlerMethod)handler);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        this.processAuditLog(request, handler);
    }


    private void processAuditLog(HttpServletRequest request, Object handler) throws LoginAuthenticationFailException, IOException {
        if(this.isAuditLogging(handler)){
            this.fillAuditLogging();
        }
    }

    private boolean isAuditLogging(Object handler){
        if(!(handler instanceof HandlerMethod)){
           return false;
        }
        return ((HandlerMethod)handler).getMethodAnnotation(AuditLog.class) != null;
    }

    private void initAuditLogInfo(HttpServletRequest request, HandlerMethod hm) throws LoginAuthenticationFailException {
        AuditLogger.enable();
        this.fillAuditLoggingFromAnnotation(request, hm);
    }

    private void fillAuditLogging(){
        AuditLogger.setSuccess(true);
    }

    private void fillAuditLoggingFromAnnotation(HttpServletRequest request, HandlerMethod hm) throws LoginAuthenticationFailException {
        AuditLog auditLog = hm.getMethodAnnotation(AuditLog.class);
        assert auditLog != null;
        AuditLogger.setOperateModule(this.getOperateModule(hm));
        AuditLogger.setOperateType(this.parseOptType(auditLog, hm));
        AuditLogger.setInfo(auditLog.info());
    }

    private String getOperateModule(HandlerMethod hm){
        AuditModule auditModule = hm.getMethodAnnotation(AuditModule.class);
        if(auditModule == null){
            auditModule = hm.getBeanType().getAnnotation(AuditModule.class);
        }
        return auditModule == null ? null : auditModule.value();
    }


    private String parseOptType(AuditLog auditLog, HandlerMethod hm){
        assert auditLog != null;
        if(StringUtils.isNotBlank(auditLog.optType())){
            return auditLog.optType();
        }
        else{
            return new AuditLogOperateTypeParser(hm.getMethod().getName()).parse();
        }
    }


}
