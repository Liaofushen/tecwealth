package com.lfs.tecwealth.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lfs.tecwealth.common.vo.LogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * LogInterceptLogor
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo_group
 * @create 2022/4/25
 * @modify 2022/4/25
 */
@Slf4j
@Component
public class LogInterceptLogor implements HandlerInterceptor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        startTime.set(System.currentTimeMillis());

        log.info("Begin api request {} uri={} params=[{}] header={}",
                request.getMethod(), request.getRequestURI(),
                getQueryString(request),
                LogVo.newLimit200(getHeaders(request)));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

        log.info("Finish api request {} uri={} cost={}",
                request.getMethod(), request.getRequestURI(),
                System.currentTimeMillis() - startTime.get());

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private String getQueryString(HttpServletRequest request) {
        String queryString = "";
        if (StringUtils.isNotEmpty(request.getQueryString())) {
            try {
                queryString = URLDecoder.decode(request.getQueryString(), UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                log.warn("URLDecoder.decode error:" + e.getMessage(), e);
            }
        }
        return queryString;
    }

    private JSONObject getHeaders(HttpServletRequest request) {
        JSONObject map = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
