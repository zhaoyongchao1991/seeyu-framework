package com.seeyu.mvc.common.model;

import java.io.Serializable;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public interface AuditLogUserHolderI {

        Serializable getUserId();

        void setUserId(Serializable userId);

        String getUserName();

        void setUserName(String userName);
}
