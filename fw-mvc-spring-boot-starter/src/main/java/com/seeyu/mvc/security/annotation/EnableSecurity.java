package com.seeyu.mvc.security.annotation;


import com.seeyu.mvc.security.configuration.SecurityConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SecurityConfiguration.class)
public @interface EnableSecurity {
}
