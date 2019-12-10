package com.seeyu.fw.auth.configure;

import com.seeyu.fw.auth.service.*;
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
        AuthSystemAccountService.class,
        AuthenticationService.class,
        AuthJwtService.class,
        AuthSystemAccountService.class,
        AuthMenuService.class,
        AuthResourceService.class,
        AuthRoleRelResourceService.class
})
@Configuration
public class ServiceAutoConfigure {
}