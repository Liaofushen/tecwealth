package com.lfs.web.beans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDto
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
public class UserDto {
    private String userName;

    private String age;

    private String mail;

    private String hobbyDto;

}
