package com.seeyu.mvc.common.model;

import java.util.List;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public interface SecurityUserHolderI {

    List<SecurityResourceHolderI> getResources();

    boolean isSystemAdmin();
}
