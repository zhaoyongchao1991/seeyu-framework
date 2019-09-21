package com.seeyu.mvc.ognl.configuration;

import com.seeyu.mvc.constant.FilterOrderConstant;
import com.seeyu.mvc.ognl.advice.RequestContextOgnlControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author seeyu
 * @date 2019/6/26
 */
@ImportAutoConfiguration({
        RequestContextOgnlControllerAdvice.class,
})
public class RequestContextOgnlAutoConfiguration {

    @Autowired
    private RequestContextOgnlControllerAdvice ognlFilter;

    @Bean
    public FilterRegistrationBean requestContextOgnlFilterRegistration() {
        FilterRegistrationBean<RequestContextOgnlControllerAdvice> registration = new FilterRegistrationBean<>();
        registration.setFilter(ognlFilter);
        registration.addUrlPatterns("/*");
        registration.setName("requestContextOgnlFilterRegistration");
        registration.setOrder(FilterOrderConstant.ORDER_OGNL_FILTER);
        return registration;
    }

}