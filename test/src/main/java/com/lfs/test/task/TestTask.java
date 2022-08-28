package com.lfs.test.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TestTask
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/7/6
 * @modify 2022/7/6
 */
@Component
@Slf4j
public class TestTask {

    /**
     * 定时计算。每天凌晨 01:00 执行一次
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void show() {
        //log.debug("0/3 * * * * *");
    }

    /**
     * 启动时执行一次，之后每隔2秒执行一次
     */
    @Scheduled(fixedRate = 1000 * 2)
    public void print() {
        //log.debug("每隔2秒执行一次");
    }

    @Configuration
    @EnableScheduling
    public static class SchedulingConfig {
    }
}
