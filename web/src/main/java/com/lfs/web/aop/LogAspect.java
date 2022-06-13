package com.lfs.web.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * LogInterceptor
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/7/21
 * @modify 2021/7/21
 */
// @Service
// @Aspect
// @Order(1)
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    static {
        System.out.println("LogInterceptor created");
    }

    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
     * <p>
     * 修饰符匹配（modifier-pattern?）
     * 返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
     * 类路径匹配（declaring-type-pattern?）
     * 方法名匹配（name-pattern）可以指定方法名 或者 代表所有, set 代表以set开头的所有方法
     * 参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；(,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
     * 异常类型匹配（throws-pattern?）
     * 其中后面跟着“?”的是可选项
     * ————————————————
     * 原文链接：https://blog.csdn.net/f641385712/article/details/83543270
     */
    // @Pointcut("execution (public * com.example.demo.service.impl.*(..))")
    public void annotationPointcut() {

    }

    //  @Around("annotationPointcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Object[] in = jp.getArgs();
        LOGGER.info("Begin:{}.{}|Req={}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), JSONObject.toJSONString(in));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object res;
        try {
            res = jp.proceed();
            System.out.println("...");
        } catch (Throwable t) {
            stopWatch.stop();
            LOGGER.error("Failed:{}.{}|CostTime={}ms", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), stopWatch.getLastTaskTimeMillis());
            LOGGER.error("Exception:{}.{}: {}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), t.getMessage(), t);
            throw t;
        }
        stopWatch.stop();
        LOGGER.info("Success:{}.{}|CostTime{}ms|Resp={}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), stopWatch.getLastTaskTimeMillis(), JSONObject.toJSONString(res));
        return res;
    }
}
