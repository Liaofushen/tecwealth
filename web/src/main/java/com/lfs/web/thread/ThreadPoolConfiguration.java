package com.lfs.web.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class ThreadPoolConfiguration {

    @Bean("commonThreadPoolExecutor")
    public ThreadPoolExecutor commonThreadPoolExecutor(
            @Value("${common.threadPool.corePoolSize:2}") Integer corePoolSize,
            @Value("${common.threadPool.maxPoolSize:8}") Integer maxPoolSize,
            @Value("${common.threadPool.deepAliveSeconds:60}") Integer deepAliveSeconds,
            @Value("${common.threadPool.queueCapacity:20}") Integer queueCapacity) {

        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                deepAliveSeconds, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
