package com.lfs.web.aop;

import com.lfs.web.BaseTest;
import com.lfs.web.aop.protocol.ApiMonitor;
import com.lfs.web.service.TestBeanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class ApiMonitorAspectTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMonitorAspectTest.class);

    @Autowired
    private TestBeanService testBeanService;

    @Test
    @ApiMonitor
    public void testAround() {
        String ans = testBeanService.testMethod("test");
        LOGGER.info(ans);
    }
}