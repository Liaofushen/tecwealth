package com.lfs.tecwealth.web.service.impl;

import com.lfs.tecwealth.web.protocol.ApiMonitor;
import com.lfs.tecwealth.web.service.TestBeanService;
import org.springframework.stereotype.Service;

/**
 * TestBeanServiceImpl
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/7/21
 * @modify 2021/7/21
 */
@Service
public class TestBeanServiceImpl implements TestBeanService {
    @Override
    @ApiMonitor
    public String testMethod(String input) {
        return "hello:" + input;
    }
}
