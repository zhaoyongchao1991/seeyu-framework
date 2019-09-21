package com.seeyu.mvc.web.filter.helper;

import com.seeyu.lang.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author seeyu
 * @date 2019/4/19
 */
public class RequestUriHelper {

    public static String getBaseRequestUri(HttpServletRequest req){
        String requestUri = req.getRequestURI();
        String contextPath = req.getContextPath();
        return StringUtils.isEmpty(requestUri) ? requestUri : requestUri.substring(contextPath.length());
    }

}
