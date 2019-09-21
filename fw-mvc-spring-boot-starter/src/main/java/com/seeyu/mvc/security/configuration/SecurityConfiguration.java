package com.seeyu.mvc.security.configuration;

import com.seeyu.mvc.constant.FilterOrderConstant;
import com.seeyu.mvc.security.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@ImportAutoConfiguration({
        SecurityFilter.class
})
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public FilterRegistrationBean securityFilterRegistration() {
        FilterRegistrationBean<SecurityFilter> registration = new FilterRegistrationBean<>();
        //添加过滤器
        registration.setFilter(securityFilter);
        //设置过滤路径，/*所有路径
        registration.addUrlPatterns("/*");
        //添加默认参数
        registration.setName("securityFilterRegistration");
        //设置优先级
        registration.setOrder(FilterOrderConstant.ORDER_SECURITY_FILTER);
        return registration;
    }

}