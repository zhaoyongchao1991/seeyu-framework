package com.seeyu.mvc.common.web.context;


import com.seeyu.core.utils.Assert;

import java.util.Locale;

/**
 * @author seeyu
 * @date 2019/3/7
 */
public class I18nContext {

    private static final String I18N_KEY_IN_REQUEST = I18nContext.class.getName() + "I18N_KEY_IN_REQUEST";


    public static Locale getUserLocale() {
        return ServletContext.getAttribute(Locale.class, I18N_KEY_IN_REQUEST, ServletContext.request_scope);
    }


    public static void putUserLocale(Locale locale) {
        Assert.ASSERT(locale != null);
        ServletContext.setAttribute(I18N_KEY_IN_REQUEST, locale, ServletContext.request_scope);
    }


}
