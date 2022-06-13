package com.lfs.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginVo
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/10
 * @modify 2022/6/10
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private Long uid;
    private String phone;
    private String password;
}
