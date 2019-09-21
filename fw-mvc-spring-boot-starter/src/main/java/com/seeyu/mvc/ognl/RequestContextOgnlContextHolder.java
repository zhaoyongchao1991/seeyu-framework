package com.seeyu.mvc.ognl;

import com.seeyu.mvc.common.web.context.ServletContext;
import ognl.OgnlContext;

/**
 * 从request中获取到parameter参数放到OGNL的root中，从requestBody获取到对象放到context中，key默认为:"_parameter"
 * 使用时，取request form的参数用{key}取值，取request body参数用{#_parameter}取
 * OGNL context
 * @author seeyu
 * @date 2019/6/28
 */
public class RequestContextOgnlContextHolder {

    private final static String OGNL_CONTEXT_KEY = RequestContextOgnlContextHolder.class.getName() + "OGNL_CONTEXT_KEY";


    public static void setOgnlRoot(Object root){
        getRequestContextOgnlContext().setRoot(root);
    }


    public static void putOgnlContextValue(String key, Object value){
        getRequestContextOgnlContext().put(key, value);
    }

    public static OgnlContext getRequestContextOgnlContext(){
        OgnlContext context = ServletContext.getAttribute(OgnlContext.class, OGNL_CONTEXT_KEY, ServletContext.request_scope);
        if(context == null){
            context = createContext();
            putRequestContextOgnlContext(context);
        }
        return context;
    }

    public static void clear(){
    }

    private static void putRequestContextOgnlContext(OgnlContext context){
        ServletContext.setAttribute(OGNL_CONTEXT_KEY, context, ServletContext.request_scope);
    }


    private static OgnlContext createContext() {
        return new OgnlContext(null, null, new OgnlDefaultMemberAccess(true));
    }

}
