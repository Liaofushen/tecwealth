package com.lfs.web.beans.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/8/10
 * @modify 2021/8/10
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;

    private Integer age;

    private String mailbox;

    private String hobby;
}
