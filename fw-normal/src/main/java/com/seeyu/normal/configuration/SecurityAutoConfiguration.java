package com.seeyu.normal.configuration;

import com.seeyu.normal.initialization.SecurityResourceLoadInitialization;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/11/19
 */
@ImportAutoConfiguration({
        SecurityResourceLoadInitialization.class
})
@Configuration
public class SecurityAutoConfiguration {
}
