package com.seeyu.core.utils;


import com.seeyu.core.exception.AlertRuntimeException;
import com.seeyu.core.exception.AssertionRuntimeException;
import com.seeyu.lang.utils.StringUtils;

/**
 * @author seeyu
 */
public class Alert {


    public static void alert(String message, Object... args) {
        throw new AlertRuntimeException(message, args);
    }

    public static void alert(boolean b, String message, Object...args){
        if(!b){
            alert(message, args);
        }
    }

    public static void equals(Object source, Object target, String message, Object...args){
        if(!source.equals(target)){
            alert(message, args);
        }
    }

    public static void notEquals(Object source, Object target, String message, Object...args){
        if(source.equals(target)){
            alert(message, args);
        }
    }

    public static void notNull(Object object, String message, Object...args){
        if(object == null){
            alert(message, args);
        }
    }

    public static void notEmpty(Object object, String message, Object...args){
        notNull(object, message);
        if(object instanceof String){
            if(StringUtils.isEmpty((String)object)){
                alert(message, args);
            }
        }
    }

    public static void notBlank(String str, String message, Object...args){
        if(StringUtils.isBlank(str)){
            alert(message, args);
        }
    }


}
