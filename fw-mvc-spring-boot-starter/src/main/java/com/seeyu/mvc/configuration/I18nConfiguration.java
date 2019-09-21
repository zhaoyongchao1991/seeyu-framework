package com.seeyu.mvc.configuration;

import com.seeyu.i18n.core.I18nMessageHolder;
import com.seeyu.lang.utils.StringUtils;
import com.seeyu.mvc.common.web.context.I18nContext;
import com.seeyu.mvc.configuration.auto.properties.I18nConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;

/**
 * @author seeyu
 * @date 2019/3/8
 */
@Configuration
public class I18nConfiguration {

    @Autowired
    private I18nConfigProperties i18nConfigProperties;


    @PostConstruct
    private void config() {
        final MessageSource messageSource = resourceBundleMessageSource();

        I18nMessageHolder.register(key -> messageSource.getMessage(key, null, I18nContext.getUserLocale()));
    }


    private MessageSource resourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("mvc-i18n/mess", getBasename());
        messageSource.setDefaultEncoding(getEncoding());
        //messageSource.setCacheMillis(cacheMillis);
        //ResourceBundleMessageSource r = new ResourceBundleMessageSource();
        return messageSource;
    }


    private String getBasename() {
        return StringUtils.isNotBlank(i18nConfigProperties.getBasename()) ? i18nConfigProperties.getBasename() : "mess";
    }


    private String getEncoding() {
        return StringUtils.isNotBlank(i18nConfigProperties.getEncoding()) ? i18nConfigProperties.getEncoding() : "utf-8";
    }

}
