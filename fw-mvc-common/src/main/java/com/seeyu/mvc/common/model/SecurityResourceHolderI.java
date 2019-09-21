package com.seeyu.mvc.common.model;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public interface SecurityResourceHolderI {

   String getResourceUri();

   Integer getResourceLevel();

   Integer getResourceActive();

   boolean isActiveState();

}
