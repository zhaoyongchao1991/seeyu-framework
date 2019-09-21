package com.seeyu.fw.security;

/**
 * @author seeyu
 * @date 2019/3/13
 */
public interface SecurityResourceHolderI {

   String getResourceUri();
   Integer getResourceLevel();
   Integer getResourceActive();

}
