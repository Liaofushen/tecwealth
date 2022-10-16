package com.lfs.tecwealth.web.protocol;

import com.lfs.tecwealth.web.aop.ApiMonitorAspect;

import java.lang.annotation.*;

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
