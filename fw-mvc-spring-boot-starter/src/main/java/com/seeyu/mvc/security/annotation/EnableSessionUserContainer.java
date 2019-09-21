package com.seeyu.mvc.security.annotation;

import com.seeyu.mvc.security.configuration.SessionLoginUserFetchConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *启用session容器来保持用户登录状态
 * @author seeyu
 * @date 2019/7/2
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SessionLoginUserFetchConfiguration.class)
public @interface EnableSessionUserContainer {


}
