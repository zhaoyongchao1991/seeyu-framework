package com.seeyu.fw.auth.configure;

import com.seeyu.fw.auth.service.AuthRoleService;
import com.seeyu.fw.auth.service.helper.RoleServiceHelper;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/11/28
 */
@ImportAutoConfiguration({
        AuthRoleService.class, RoleServiceHelper.class

})
@Configuration
public class ServiceAutoConfigure {
}
