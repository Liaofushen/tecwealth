package com.lfs.test.beans.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
@ApiModel(value = "用户")
public class User {

    @JSONField(name = "user_name")
    @JsonProperty("user_name")
    @NotNull
    @ApiModelProperty()
    private String userName;

    @ApiModelProperty()
    @NotNull
    private Integer age;

    @ApiModelProperty()
    private String mailbox;

    @ApiModelProperty()
    private String hobby;
}
