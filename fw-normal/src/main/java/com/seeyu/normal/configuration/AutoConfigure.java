package com.seeyu.normal.configuration;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/6/12
 */

@ImportAutoConfiguration({
        GlobalAutoConfigure.class,
        DatabaseUpgradeAutoConfigure.class,
})
@Configuration
public class AutoConfigure {
}
