package com.lfs.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lfs.common.vo.LogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

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
public class LogInterceptLogor implements HandlerInterceptor, ResponseBodyAdvice {

//    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        startTime.set(System.currentTimeMillis());
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//        log.info("Begin api request uri={} query={} method={} header={} ip={} host={}",
//                request.getRequestURI(),
//                getQueryString(requestWrapper),
//                requestWrapper.getMethod(),
//                LogVo.newLimit100(getHeaders(requestWrapper)),
//                getRemoteIP(requestWrapper),
//                requestWrapper.getRemoteHost());

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

//         ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//         String responseBody = getBody(responseWrapper.getContentAsByteArray(), UTF_8.name());
//
//         log.info("End api response uri={} cost={}ms httpCode={} bodyCode={} body={}",
//                 request.getRequestURI(),
//                 System.currentTimeMillis() - startTime.get(),
//                 response.getStatus(),
//                 getBodyCode(responseBody),
//                 LogVo.newLimit200(responseBody));
//
//         responseWrapper.copyBodyToResponse();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private Integer getBodyCode(String responseBody) {
        if (responseBody == null || responseBody.isEmpty()) {
            return null;
        }
        try {
            JSONObject bodyJson = JSON.parseObject(responseBody);
            return bodyJson.getInteger("code");
        } catch (Exception e) {
            log.warn("Fail get bodyCode err={}", e.getMessage(), e);
        }
        return null;
    }

    private String getBody(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (Exception e) {
            log.warn("Fail get body err={}", e.getMessage(), e);
        }
        return "";
    }

    private String getQueryString(ContentCachingRequestWrapper request) {
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

    private JSONObject getHeaders(ContentCachingRequestWrapper request) {
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

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return null;
    }
}
