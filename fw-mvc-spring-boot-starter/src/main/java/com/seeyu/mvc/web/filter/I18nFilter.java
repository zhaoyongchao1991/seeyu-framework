package com.seeyu.mvc.web.filter;

import com.seeyu.lang.utils.StringUtils;
import com.seeyu.mvc.common.web.context.I18nContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * @author seeyu
 * @date 2019/3/6
 */
public class I18nFilter implements Filter {

    private static final Locale DEFAULT_LOCALE = Locale.CHINA;

    //private static final String USER_LOCALE_IN_REQUEST_KEY = "USER_LOCALE_IN_REQUEST_KEY";

    private static final String USER_LOCALE_IN_COOKIE_LANGUAGE_KEY = "USER_LOCALE_IN_COOKIE_LANGUAGE_KEY";

    private static final String USER_LOCALE_IN_COOKIE_COUNTRY_KEY = "USER_LOCALE_IN_COOKIE_COUNTRY_KEY";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        genUserLocale((HttpServletRequest)servletRequest);

        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }


    private void genUserLocale(HttpServletRequest request){
        Locale userLocale = getUserLocaleFromSession(request);
        if(userLocale == null){
            userLocale = getUserLocaleFromCookie(request);
            if(userLocale == null){
                userLocale = DEFAULT_LOCALE;
            }
            I18nContext.putUserLocale(userLocale);
        }
    }


    private Locale getUserLocaleFromSession(HttpServletRequest request){
        return I18nContext.getUserLocale();
    }


    private Locale getUserLocaleFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return  null;
        }
        String language = null;
        String country = null;
        for(Cookie cookie : cookies){
            if(USER_LOCALE_IN_COOKIE_LANGUAGE_KEY.equals(cookie.getName())){
                language = cookie.getValue();
            }
            else if(USER_LOCALE_IN_COOKIE_COUNTRY_KEY.equals(cookie.getName())){
                country = cookie.getValue();
            }
        }
        if(StringUtils.isBlank(language) || StringUtils.isBlank(country)){
            return null;
        }
        return new Locale(language, country);
    }


}
