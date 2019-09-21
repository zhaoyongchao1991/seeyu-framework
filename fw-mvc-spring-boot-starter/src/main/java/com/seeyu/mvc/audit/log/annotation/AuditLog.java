package com.seeyu.mvc.audit.log.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author seeyu
 * @date 2019/4/2
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AuditLog {
    /**optInfo
     * @return
     */
    @AliasFor("info")
    String value() default "";

    String optType() default "";

    @AliasFor("value")
    String info() default "";
}