package com.seeyu.mvc.audit.log;

import com.seeyu.mvc.common.model.AuditLogHolderI;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seeyu
 * @date 2019/4/2
 */
@Data
class BaseAuditLog implements AuditLogHolderI {


    /**
     * 调用方法上是否启用了 @AuditLog注解
     */
    private boolean enable = false;

    private String operateModule;
    private String operateType;
    private Serializable operateUserId;
    private String operateUserName;
    private Date operateTime;
    /**
     * 响应时间
     */
    private Date responseTime;
    private String info;

    /**
     * auditLog 参数，主要给info中{}使用
     */
    private Object[] arguments;

    /**
     * request 请求参数
     */
    private Object parameter;

    private boolean success;


    @Override
    public String getOperateModule() {
        return operateModule;
    }

    @Override
    public String getOperateType() {
        return operateType;
    }

    @Override
    public Serializable getOperateUserId() {
        return operateUserId;
    }

    @Override
    public String getOperateUserName() {
        return operateUserName;
    }

    @Override
    public Date getOperateTime() {
        return operateTime;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public Long getCost() {
        if(this.operateTime != null && this.responseTime != null){
            return this.responseTime.getTime() - this.operateTime.getTime();
        }
        return null;
    }

    @Override
    public Object getParameter() {
        return parameter;
    }

}
