package com.lfs.tecwealth.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class RequestUtil {

    public static final String LOGID = "LOGID";

    public static Integer getBodyCode(String responseBody) {
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

    public static String getBody(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (Exception e) {
            log.warn("Fail get body err={}", e.getMessage(), e);
        }
        return "";
    }

    public static String getQueryString(HttpServletRequest request) {
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

    public static JSONObject getHeaders(HttpServletRequest request) {
        JSONObject map = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public static String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static String getLogId(HttpServletRequest req) {
        String val = req.getHeader(LOGID);
        if (!org.thymeleaf.util.StringUtils.isEmpty(val)) {
            log.debug("Suc get logId from header, logId={}", val);
            return val;
        }
        return null;
    }

    public static void setLodId(String logId) {
        MDC.put(LOGID, logId);
    }

    public static void removeLodId() {
        MDC.remove(LOGID);
    }
}
