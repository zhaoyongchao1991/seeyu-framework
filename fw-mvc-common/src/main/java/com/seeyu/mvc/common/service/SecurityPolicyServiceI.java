package com.seeyu.mvc.common.service;


import com.seeyu.mvc.common.model.SecurityResourceHolderI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/3/15
 */
public interface SecurityPolicyServiceI {


    /**
     * 获取全部的资源数据
     * @return
     */
    default List<SecurityResourceHolderI> getResources(){
        return new ArrayList<SecurityResourceHolderI>(0);
    }

    /**
     * 是否跳过认证
     * @param uri
     * @return
     */
    default boolean isSkipAuthentication(String uri){
        return false;
    }

    SecurityResourceHolderI getResources(String uri);

}
