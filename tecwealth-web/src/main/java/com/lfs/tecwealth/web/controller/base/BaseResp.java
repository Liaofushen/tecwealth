package com.lfs.tecwealth.web.controller.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BaseResp
 *
 * @author fushenliao
 * @version 1.0.0
 * @project im-callrecord
 * @create 2022/7/4
 * @modify 2022/7/4
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResp<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> BaseResp success(T data) {
        return new BaseResp<>(200, "ok", data);
    }

    public static BaseResp fail() {
        return new BaseResp<>(1000, "fail", null);
    }

    public static BaseResp fail(Integer code, String msg) {
        return new BaseResp<>(code, msg, null);
    }
}
