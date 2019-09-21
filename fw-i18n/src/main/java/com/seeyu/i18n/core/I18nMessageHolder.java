package com.seeyu.i18n.core;

import com.seeyu.i18n.exception.MessageException;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;

/**
 * @author seeyu
 * @date 2019/3/6
 */
public class I18nMessageHolder {

    private static I18nMessageHolder i18nMessageHolder = null;

    private static II18nMessageProcessor ii18nMessageProcessor;


    private I18nMessageHolder(II18nMessageProcessor processor){

    }


    public static void register(II18nMessageProcessor processor){
        ii18nMessageProcessor = processor;
    }


    public static String getI18nMessage(String key) throws MessageException {
        try{
            return ii18nMessageProcessor.getMessage(key);
        }
        catch (Exception e){
            throw new MessageException();
        }

    }




}
