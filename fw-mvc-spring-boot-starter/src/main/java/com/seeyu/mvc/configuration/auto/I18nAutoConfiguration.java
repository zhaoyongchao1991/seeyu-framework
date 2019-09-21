package com.seeyu.mvc.configuration.auto;

import com.seeyu.mvc.advice.I18nInterceptorAdvice;
import com.seeyu.mvc.configuration.I18nConfiguration;
import com.seeyu.mvc.configuration.auto.properties.I18nConfigProperties;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author seeyu
 * @date 2019/6/26
 */
@ImportAutoConfiguration({
        I18nConfiguration.class,
        I18nInterceptorAdvice.class,
})
@EnableConfigurationProperties({I18nConfigProperties.class})
public class I18nAutoConfiguration {
}
