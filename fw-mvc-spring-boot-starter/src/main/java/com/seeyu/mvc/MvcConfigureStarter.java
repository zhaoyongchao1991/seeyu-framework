package com.seeyu.mvc;

import com.seeyu.mvc.configuration.auto.AutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/3/8
 */
@Configuration
@ImportAutoConfiguration({AutoConfiguration.class})
public class MvcConfigureStarter {


}
