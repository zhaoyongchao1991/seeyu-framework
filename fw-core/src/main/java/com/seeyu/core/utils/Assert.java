package com.seeyu.core.utils;


import com.seeyu.core.exception.AssertionRuntimeException;
import com.seeyu.lang.utils.StringUtils;

/**
 * @author seeyu
 */
public class Assert {

    public static void ASSERT (){
        throw new AssertionRuntimeException();
    }

    public static void ASSERT (String message, Object... args){
        throw new AssertionRuntimeException(message, args);
    }

    public static void ASSERT (boolean b){
        if(!b){
            ASSERT();
        }
    }

    public static void ASSERT (boolean b, String message, Object... args){
        if(!b){
            ASSERT(message, args);
        }
    }

    public static void equals(Object source, Object target, String message, Object... args){
        if(!source.equals(target)){
            ASSERT(message, args);
        }
    }

    public static void notEquals(Object source, Object target, String message, Object... args){
        if(source.equals(target)){
            ASSERT(message, args);
        }
    }

    public static void notNull(Object object, String message, Object... args){
        if(object == null){
            ASSERT(message, args);
        }
    }

    public static void notEmpty(Object object, String message, Object... args){
        notNull(object, message);
        if(object instanceof String){
            if(StringUtils.isEmpty((String)object)){
                ASSERT(message, args);
            }
        }
    }

    public static void notBlank(String str, String message, Object... args){
        if(StringUtils.isBlank(str)){
            ASSERT(message, args);
        }
    }


}
