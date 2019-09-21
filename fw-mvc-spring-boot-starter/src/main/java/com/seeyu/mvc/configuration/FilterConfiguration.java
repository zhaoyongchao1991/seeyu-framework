package com.seeyu.mvc.configuration;

import com.seeyu.mvc.constant.FilterOrderConstant;
import com.seeyu.mvc.web.filter.I18nFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean i18nFilterRegistration() {
        FilterRegistrationBean<I18nFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new I18nFilter());
        registration.addUrlPatterns("/*");
        registration.setName("i18nFilterRegistration");
        registration.setOrder(FilterOrderConstant.ORDER_I18N_FILTER);
        return registration;
    }

}