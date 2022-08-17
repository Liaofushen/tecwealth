package com.lfs.web.thread;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolConfiguration
 *
 * @author fushenliao
 * @version 1.0.0
 * @create 2022/4/21
 * @modify 2022/4/21
 */
@Configuration
@Slf4j
public class ThreadPoolConfiguration {


    @Configuration
    @ConfigurationProperties(prefix = "common.thread-pool")
    @Data
    public static class  ThreadPoolConfig {
        private Integer corePoolSize;
        private Integer maxPoolSize;
        private Integer deepAliveSeconds;
        private Integer queueCapacity;
    }

    @Bean("commonThreadPoolExecutor")
    public ThreadPoolExecutor commonThreadPoolExecutor(ThreadPoolConfig threadPoolConfig) {

        ThreadPoolExecutor executro = new ThreadPoolExecutor(
                threadPoolConfig.corePoolSize,
                threadPoolConfig.maxPoolSize,
                threadPoolConfig.deepAliveSeconds, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(threadPoolConfig.queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("commonThreadPoolExecutor:{}", JSON.toJSONString(executro));
        log.info("queueCapacity:{}", threadPoolConfig.queueCapacity);
        return executro;
    }

}
