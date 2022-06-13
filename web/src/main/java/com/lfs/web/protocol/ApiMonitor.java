package com.lfs.web.protocol;

import com.lfs.web.aop.ApiMonitorAspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ApiMonitor
 *
 * @author fushenliao
 * @version 1.0.0
 * @create 2021/7/22
 * @modify 2021/7/22
 * @see ApiMonitorAspect
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMonitor {
    String value() default "";
}
