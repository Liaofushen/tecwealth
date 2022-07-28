package com.lfs.web.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * ApiMonitorAspect
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/7/21
 * @modify 2021/7/21
 */
// @Component
// @Aspect
// @Order(1)

public class ApiMonitorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMonitorAspect.class);

    static {
        System.out.println("ApiMonitorAspect created");
    }

    @Pointcut("@annotation(com.lfs.web.aop.protocol.ApiMonitor)")
    public void annotationPointcut() {

    }

    @Around("annotationPointcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Object[] in = jp.getArgs();
        LOGGER.info("Begin to {}.{}|input={}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), JSONObject.toJSONString(in));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object res;
        try {
            res = jp.proceed();
        } catch (Throwable t) {
            stopWatch.stop();
            LOGGER.error("Failed to {}.{}|costTime={}ms", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), stopWatch.getLastTaskTimeMillis());
            LOGGER.error("Exception to {}.{}: {}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), t.getMessage(), t);
            throw t;
        }
        stopWatch.stop();
        LOGGER.info("Succeed to {}.{}|costTime={}ms|output={}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), stopWatch.getLastTaskTimeMillis(), JSONObject.toJSONString(res));
        return res;
    }
}
