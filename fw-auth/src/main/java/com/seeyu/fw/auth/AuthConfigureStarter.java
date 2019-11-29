package com.seeyu.fw.auth;

import com.seeyu.fw.auth.configure.AutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/3/8
 */
@MapperScan(basePackages = "com.seeyu.fw.auth.mapper")
@ImportAutoConfiguration({AutoConfigure.class})
@Configuration
public class AuthConfigureStarter {


}