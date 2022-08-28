package com.lfs.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * BaseTest
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/7/21
 * @modify 2021/7/21
 */
@SpringBootTest(classes = {WebApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class BaseTest extends AbstractTestNGSpringContextTests {

}