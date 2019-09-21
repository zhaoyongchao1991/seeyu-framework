package com.seeyu.mvc.common.service;

import com.seeyu.mvc.common.model.AuditLogHolderI;

/**
 * @author seeyu
 * @date 2019/3/15
 */
public interface AuditLogServiceI {

    /**
     * 保存审计日志
     * @param auditLogHolder
     */
    void saveAuditLog(AuditLogHolderI auditLogHolder);

}
