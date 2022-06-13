package com.lfs.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.thymeleaf.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
@WebFilter(filterName = "Filter02LogId", urlPatterns = "/*")
public class Filter02LogId implements Filter {

    private static final String LOGID = "LOGID";
    private static final String[] LOG_ID_HDS = new String[]{LOGID, "LOG-ID"};
    private static final String[] LOG_ID_PRAMS = new String[]{"logid", "logId"};

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

        String logId = getLogId(req);
        if (StringUtils.isEmpty(logId)) {
            logId = System.currentTimeMillis() + "";
            log.info("No LOGID from http header. use default : " + logId);
        }
        MDC.put(LOGID, logId);
        log.debug("End doFilter {}", this.getClass().getSimpleName());
        chain.doFilter(req, response);
    }

    private String getLogId(HttpServletRequest req) {
        for (String key : LOG_ID_HDS) {
            String val = req.getHeader(key);
            if (!StringUtils.isEmpty(val)) {
                log.debug("Suc get logId from header, logId={}", val);
                return val;
            }
        }
        log.debug("Begin get logId from parameter");
        for (String key : LOG_ID_PRAMS) {
            String val = req.getParameter(key);
            if (!StringUtils.isEmpty(val)) {
                log.debug("Suc get logId from parameter, logId={}", val);
                return val;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        MDC.remove(LOGID);
    }
}
