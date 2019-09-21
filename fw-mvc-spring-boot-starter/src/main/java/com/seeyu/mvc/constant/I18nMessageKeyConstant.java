package com.seeyu.mvc.constant;

import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * @author seeyu
 * @date 2019/3/6
 */
@Data
public class I18nMessageKeyConstant {

    public static final String  SERVER_SECURITY_ALERT_DISABLE_RESOURCE = "server.security.alert.disableResource";

    public static final String SERVER_SECURITY_ALERT_AUTHENTICATION_FAILED = "server.security.alert.authenticationFailed";

    public static final String SERVER_SECURITY_ALERT_LOGIN_AUTHENTICATION_FAILED = "server.security.alert.loginAuthenticationFailed";

    public static final String SERVER_EXCEPTION_ALERT_LOGIN_AUTHENTICATION_EXCEPTION = "server.exception.alert.loginAuthenticationException";

    public static final String SERVER_EXCEPTION_ALERT_AUTHENTICATION_FAILED_EXCEPTION = "server.exception.alert.authenticationFailedException";

    public static final String SERVER_EXCEPTION_ALERT_SERVER_EXCEPTION = "server.exception.alert.serverException";

    public static final String SERVER_TIPS_ALERT_SUCCESSFUL_OPERATION = "server.tips.alert.SuccessfulOperation";


}
