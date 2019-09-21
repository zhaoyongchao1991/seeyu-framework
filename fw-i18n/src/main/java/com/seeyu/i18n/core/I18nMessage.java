package com.seeyu.i18n.core;


import com.seeyu.i18n.exception.MessageException;
import com.seeyu.lang.utils.MessageFormat;

/**
 * @author seeyu
 * @date 2019/3/5
 */
public class I18nMessage {

    public static String valueOf(String key){
       return valueOf(key, null);
    }


    public static String valueOf(String key, Object...arguments){
        return MessageFormat.format(getI18nMessage(key), genMessageArguments(arguments));
    }


    private static String getI18nMessage(String key){
        try{
            return I18nMessageHolder.getI18nMessage(key);
        }
        catch (MessageException e){
            return key;
        }
    }


    private static String[] genMessageArguments(Object[] arguments){
        if(arguments == null){
            return null;
        }
        String[] args = new String[arguments.length];
        for(int i = 0; i < arguments.length; i++){
            if(arguments[i] == null){
                continue;
            }
            args[i] = getI18nMessage(String.valueOf(arguments[i]));
        }
        return args;
    }


}
