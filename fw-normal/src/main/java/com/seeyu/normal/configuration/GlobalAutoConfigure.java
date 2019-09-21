package com.seeyu.normal.configuration;

import com.seeyu.normal.initialization.ServiceStartedInitialization;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/6/12
 */
@ImportAutoConfiguration({
        ServiceStartedInitialization.class,
})
@Configuration
public class GlobalAutoConfigure {
}
