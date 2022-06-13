package com.lfs.web.configuration;

import com.lfs.web.interceptor.LogInterceptLogor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * InterceptorConfiguration
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo_group
 * @create 2022/4/25
 * @modify 2022/4/25
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private LogInterceptLogor logInterceptLogor;

    /**
     * Override this method to add Spring MVC interceptors for
     * pre- and post-processing of controller invocation.
     *
     * @param registry
     * @see InterceptorRegistry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptLogor)
                .addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
