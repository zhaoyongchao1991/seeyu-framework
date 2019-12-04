package com.seeyu.fw.auth.configure;

import com.seeyu.fw.auth.service.AuthAccountRelRoleService;
import com.seeyu.fw.auth.service.AuthAccountService;
import com.seeyu.fw.auth.service.AuthRoleService;
import com.seeyu.fw.auth.service.AuthSystemAccountService;
import com.seeyu.fw.auth.service.helper.AuthAccountServiceHelper;
import com.seeyu.fw.auth.service.helper.AuthRoleServiceHelper;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@ImportAutoConfiguration({
        AuthRoleService.class, AuthRoleServiceHelper.class,
        AuthAccountService.class, AuthAccountServiceHelper.class,
        AuthAccountRelRoleService.class,
        AuthSystemAccountService.class
})
@Configuration
public class ServiceAutoConfigure {
}