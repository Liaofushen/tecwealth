package com.lfs.tecwealth.web.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * ThreadPoolConfiguration
 *
 * @author fushenliao
 * @version 1.0.0
 * @create 2022/4/21
 * @modify 2022/4/21
 */
@Configuration
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
        return executro;
    }

}
