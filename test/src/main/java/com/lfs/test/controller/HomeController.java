package com.lfs.test.controller;

import com.lfs.test.controller.base.BaseController;
import com.lfs.test.controller.base.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @Author: qiaolh 2021/4/22 7:41 下午
 */
@RestController
@Slf4j
@RequestMapping("/")
public class HomeController extends BaseController {

    @GetMapping("/")
    public BaseResp health() {
        return BaseResp.success(null);
    }

}
