package com.lfs.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @Author: qiaolh 2021/4/22 7:41 下午
 */
@RestController
@Slf4j
@RequestMapping("/tecwealth/api")
public class HomeController {

    @RequestMapping("/")
    public Map<String, Object> health() {
        Map<String, Object> ans = new HashMap<>();
        ans.put("code", 0);
        ans.put("msg", "ok");
        Map<String, Object> data = new HashMap<>();
        data.put("text", "hello word!");
        ans.put("data", data);
        return ans;
    }

}
