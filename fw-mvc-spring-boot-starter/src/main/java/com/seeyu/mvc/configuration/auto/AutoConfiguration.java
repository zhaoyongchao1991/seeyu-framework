package com.seeyu.mvc.configuration.auto;

import com.seeyu.mvc.ognl.configuration.RequestContextOgnlAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

/**
 * @author seeyu
 * @date 2019/6/26
 */
@ImportAutoConfiguration({
        MvcAutoConfiguration.class,
        I18nAutoConfiguration.class,
        RequestContextOgnlAutoConfiguration.class,
        OtherAutoConfiguration.class
})
public class AutoConfiguration {
}
