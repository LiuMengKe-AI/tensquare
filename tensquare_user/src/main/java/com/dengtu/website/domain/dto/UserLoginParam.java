package com.dengtu.website.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserLoginParam
 * @Author: LMK
 * @Date: 2020/5/7 13:37
 * @Version: 1.0
 **/
@Data
public class UserLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;
}
