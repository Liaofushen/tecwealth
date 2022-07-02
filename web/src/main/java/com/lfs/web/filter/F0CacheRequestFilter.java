package com.lfs.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

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

/**
 * Filter02LogId
 * <p>
 * *@WebFilter has no element to define the order of filter of execution.
 * We need to define order in web.xml.
 * This may be because if we decide order of filter at class level using @WebFilter,
 * then what if we include third party library which has filter with same order.`
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo_group
 * @create 2022/4/8
 * @modify 2022/4/8
 */
@Slf4j
//@WebFilter(filterName = "f0CacheRequestFilter", urlPatterns = "/*")
public class F0CacheRequestFilter implements Filter {

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
        ContentCachingRequestWrapper wreq = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper wresp = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // application/x-www-form-urlencoded方式下getParameter会将body内容放到parameter里,
        // ServletRequest.getInputStream只能读取一次，读取一次后会自动清空
        // 用ContentCachingRequestWrapper调req.getInputStream()缓存起来
        // String contentType = req.getContentType();
        // if (contentType != null && contentType.contains("application/x-www-form-urlencoded")) {
        //     try {
        //         req.getInputStream();
        //     } catch (Exception e) {
        //         log.error("Fail to getInputStream in getLodId");
        //     }
        // }
        log.debug("End doFilter {}", this.getClass().getSimpleName());
        chain.doFilter(wreq, wresp);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }
}
