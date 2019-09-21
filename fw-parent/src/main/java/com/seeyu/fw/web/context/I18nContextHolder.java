package com.seeyu.fw.web.context;


import java.util.Locale;

/**
 * @author seeyu
 * @date 2019/3/7
 */
public class I18nContextHolder {

    public static Locale getUserLocale(){
        return I18nFilter.getUserLocale();
    }

}
