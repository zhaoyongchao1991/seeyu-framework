package com.seeyu.fw.web.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author seeyu
 * @date 2019/3/6
 */
public class ServletContextHolder {

    public static final int request_scope = 0;

    public static final int session_scope = 2;


    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }


    public static HttpServletResponse getResponse(){
        return getServletRequestAttributes().getResponse();
    }


    public static HttpSession getSession() {
        return getRequest().getSession();
    }


    public static <T> T getAttribute(Class<T> clazz, String name, int scope) {
        return (T)getServletRequestAttributes().getAttribute(name, scope);
    }


    public static void setAttribute(String name, Object value, int scope) {
        getServletRequestAttributes().setAttribute(name, value, scope);
    }


    public static void removeAttribute(String name, int scope) {
        getServletRequestAttributes().removeAttribute(name, scope);
    }


    private static ServletRequestAttributes getServletRequestAttributes(){
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()));
    }


}
