package com.lfs.tecwealth.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * JsonUtils
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/12
 * @modify 2022/6/12
 */
public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String toStr(Object o) {
        if (o == null) {
            return "";
        }
        return objectMapper.writeValueAsString(o);
    }

    // public static void main(String[] args) {
    //     UserVo userVo = new UserVo();
    //     userVo.setUid(234234L);
    //     userVo.setUname("lisa");
    //     System.out.println(toStr(userVo));
    // }
}
