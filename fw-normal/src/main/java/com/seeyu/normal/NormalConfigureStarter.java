package com.seeyu.normal;

import com.seeyu.normal.configuration.AutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/3/8
 */
@MapperScan(basePackages = "com.seeyu.normal.dao")
@ImportAutoConfiguration({AutoConfigure.class})
@Configuration
public class NormalConfigureStarter {

}



