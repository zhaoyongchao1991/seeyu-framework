package com.seeyu.mvc.audit.log.annotation;

import com.seeyu.mvc.audit.log.configuration.AuditLogConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author seeyu
 * @date 2019/6/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuditLogConfiguration.class)
public @interface EnableAuditLog {


}
