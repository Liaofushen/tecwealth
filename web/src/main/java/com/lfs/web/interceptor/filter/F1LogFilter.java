package com.lfs.web.interceptor.filter;

import com.lfs.common.vo.LogVo;
import com.lfs.web.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lfs.web.util.RequestUtil.getHeaders;
import static com.lfs.web.util.RequestUtil.getQueryString;

/**
 * Filter02LogId
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo_group
 * @create 2022/4/8
 * @modify 2022/4/8
 */
@Slf4j
//@WebFilter(filterName = "f1LogFilter", urlPatterns = "/*")
public class F1LogFilter implements Filter {

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("End init {}", this.getClass().getSimpleName());
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String logId = RequestUtil.getLogId(req);
        long startTime = System.currentTimeMillis();

        if (StringUtils.isEmpty(logId)) {
            logId = "" + startTime;
            log.info("No Find {} use default {}={}", RequestUtil.LOGID, RequestUtil.LOGID, logId);
        }

        RequestUtil.setLodId(logId);

        log.info("Begin api request uri {}={} params=[{}] header={}",
                req.getMethod(),
                req.getRequestURI(),
                getQueryString(req),
                LogVo.newNoLimit(getHeaders(req)));

        chain.doFilter(req, response);

        log.info("End api response uri {}={} cost={}ms code={}",
                req.getMethod(),
                req.getRequestURI(),
                System.currentTimeMillis() - startTime,
                resp.getStatus());
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        RequestUtil.removeLodId();
    }
}
