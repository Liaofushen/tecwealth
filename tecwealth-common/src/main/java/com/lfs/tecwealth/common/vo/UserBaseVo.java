package com.lfs.tecwealth.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserBaseVo
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/10
 * @modify 2022/6/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseVo {
    private Long uid;
    private String uname;
}
