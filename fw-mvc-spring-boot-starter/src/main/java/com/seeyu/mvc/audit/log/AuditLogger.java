package com.seeyu.mvc.audit.log;

import com.seeyu.mvc.common.model.AuditLogHolderI;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seeyu
 * @date 2019/6/28
 */
public class AuditLogger {

    private final static ThreadLocal<BaseAuditLog> THREAD_AUDIT_LOGGING = new ThreadLocal<>();


    public static void setOperateModule(String operateModule){
        get().setOperateModule(operateModule);
    }

    public static void setOperateType(String operateType){
        get().setOperateType(operateType);

    }

    public static void setOperateUserId(Serializable operateUserId){
        get().setOperateUserId(operateUserId);

    }

    public static void setOperateUserName(String operateUserName){
        get().setOperateUserName(operateUserName);

    }

    public static void setOperateTime(Date operateTime){
        get().setOperateTime(operateTime);
    }

    public static void setInfo(String info){
        get().setInfo(info);
    }

    public static void setArguments(Object[] arguments){
        get().setArguments(arguments);
    }

    public static Object[] getArguments(){
        return get().getArguments();
    }

    public static void setResponseTime(Date responseTime){
        get().setResponseTime(responseTime);
    }

    public static void  setParameter(Object parameter){
        get().setParameter(parameter);
    }

    public static void setSuccess(Boolean success){
        get().setSuccess(success);
    }

    public static void enable(){
        get().setEnable(true);
    }

    public static boolean isEnable(){
        return get().isEnable();
    }

    public static void clear(){
        THREAD_AUDIT_LOGGING.remove();
    }

    public static void init(){
        BaseAuditLog auditLogging = new BaseAuditLog();
        auditLogging.setOperateTime(new Date());
        set(auditLogging);
    }

    public static AuditLogHolderI getAuditLog(){
        BaseAuditLog auditLog = THREAD_AUDIT_LOGGING.get();
        if(auditLog == null){
            auditLog = new BaseAuditLog();
            THREAD_AUDIT_LOGGING.set(auditLog);
        }
        return auditLog;
    }

    private static void set(BaseAuditLog auditLog){
        THREAD_AUDIT_LOGGING.set(auditLog);
    }

    private static BaseAuditLog get(){
        return (BaseAuditLog)getAuditLog();
    }



}
