package com.seeyu.mvc.audit.log.configuration;

import com.seeyu.mvc.audit.log.interceptor.AuditLogInterceptor;
import com.seeyu.mvc.audit.log.filter.AuditLogFilter;
import com.seeyu.mvc.constant.FilterOrderConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author seeyu
 * @date 2019/6/26
 */
@ImportAutoConfiguration({
        AuditLogFilter.class,
        AuditLogInterceptor.class,
})
public class AuditLogConfiguration  implements WebMvcConfigurer {

    @Autowired
    private AuditLogInterceptor auditLogInterceptor;
    @Autowired
    private AuditLogFilter auditLogFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(auditLogInterceptor).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean auditLogFilterRegistration() {
        FilterRegistrationBean<AuditLogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(auditLogFilter);
        registration.addUrlPatterns("/*");
        registration.setName("auditLogFilterRegistration");
        registration.setOrder(FilterOrderConstant.ORDER_AUDIT_LOG_FILTER);
        return registration;
    }
}
