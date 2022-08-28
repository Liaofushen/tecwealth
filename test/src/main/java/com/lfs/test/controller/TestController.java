package com.lfs.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lfs.test.beans.domain.User;
import com.lfs.test.controller.base.BaseController;
import com.lfs.test.controller.base.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * HelloController
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo_group
 * @create 2022/4/8
 * @modify 2022/4/8
 */
@Controller
@RequestMapping("/test")
@Slf4j
@Validated
@Api(value = "测试相关接口", tags = "测试相关接口")
public class TestController extends BaseController {

    @ResponseBody
    @PostMapping("/user")
    @ApiOperation(value = "testUser")
    public BaseResp<User> testUser(@Validated @RequestBody User user) {
        return BaseResp.success(user);
    }

    @GetMapping("/hello")
    @ResponseBody
    public Map<String, Object> health() {
        Map<String, Object> ans = new HashMap<>();
        ans.put("code", 0);
        ans.put("msg", "ok");
        Map<String, Object> data = new HashMap<>();
        data.put("field", "hello word");
        ans.put("data", data);
        return ans;
    }

    @ResponseBody
    @PostMapping(value = "/test")
    public Map<String, Object> meetingInvitation(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        String s = readStream(is);
        log.info("body={}", s);
        JSONObject ans = JSON.parseObject(s);
        if (ans == null) {
            ans = new JSONObject();
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("parameterMap={}", JSON.toJSONString(parameterMap.entrySet()));
        ans.put("parameterMap", parameterMap.entrySet());
        return ans;
    }

    public static String readStream(InputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
