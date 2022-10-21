package com.lfs.tecwealth.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lfs.tecwealth.web.controller.base.BaseController;
import com.lfs.tecwealth.web.controller.base.BaseResp;

/**
 * Desc:
 *
 * @Author: qiaolh 2021/4/22 7:41 下午
 */
@RestController
@RequestMapping("/")
public class HomeController extends BaseController {

    @GetMapping("/")
    public BaseResp<?> health() {
        return BaseResp.success(null);
    }

}
