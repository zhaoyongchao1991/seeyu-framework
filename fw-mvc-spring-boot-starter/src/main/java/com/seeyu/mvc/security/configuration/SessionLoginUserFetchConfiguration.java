package com.seeyu.mvc.security.configuration;

import com.seeyu.mvc.constant.FilterOrderConstant;
import com.seeyu.mvc.security.filter.SessionLoginUserFetchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@ImportAutoConfiguration({
        SessionLoginUserFetchFilter.class
})
public class SessionLoginUserFetchConfiguration {

    @Autowired
    private SessionLoginUserFetchFilter sessionLoginUserFetchFilter;

    @Bean
    public FilterRegistrationBean loginUserFetchFilterRegistration() {
        FilterRegistrationBean<SessionLoginUserFetchFilter> registration = new FilterRegistrationBean<>();
        //添加过滤器
        registration.setFilter(sessionLoginUserFetchFilter);
        //设置过滤路径，/*所有路径
        registration.addUrlPatterns("/*");
        //添加默认参数
        registration.setName("loginUserFetchFilterRegistration");
        //设置优先级
        registration.setOrder(FilterOrderConstant.ORDER_FETCH_LOGIN_USER_FILTER);
        return registration;
    }

}
