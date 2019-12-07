package com.seeyu.fw.auth.configure;

import com.seeyu.fw.auth.config.TokenConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/12/5
 */
@ImportAutoConfiguration({
        TokenConfig.class
})
@Configuration
public class ConfigAutoConfigure {
}
