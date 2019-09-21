package com.seeyu.mvc.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public interface AuditLogHolderI {

        String getOperateModule();

        String getOperateType();

        Serializable getOperateUserId();

        String getOperateUserName();

        Date getOperateTime();

        String getInfo();

        Long getCost();

        Object getParameter();

        boolean isSuccess();
}
