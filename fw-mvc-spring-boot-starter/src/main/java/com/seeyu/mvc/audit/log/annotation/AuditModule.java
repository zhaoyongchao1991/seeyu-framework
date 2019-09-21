package com.seeyu.mvc.audit.log.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author seeyu
 * @date 2019/4/2
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface AuditModule {
    String value();
}
