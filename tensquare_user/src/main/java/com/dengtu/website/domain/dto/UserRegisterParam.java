package com.dengtu.website.domain.dto;

import com.dengtu.website.common.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: UserRegisterParam
 * @Author: LMK
 * @Date: 2020/5/6 17:30
 * @Version: 1.0
 **/
@Data
public class UserRegisterParam {
    @ApiModelProperty(value = "手机号", required = true)
    @Pattern(regexp = "^(13[0-9]|14[5-9]|15[0-3,5-9]|16[2,5,6,7]|17[0-8]|18[0-9]|19[0-3,5-9])\\d{8}$")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "验证码", required = true)
    @NotBlank(message = "验证码不能为空")
    private String vcode;

    public static void main(String[] args) {

        long l = (System.currentTimeMillis() + 604800 * 1000)/1000;
        System.out.println(DateUtil.timeStamp2Date(String.valueOf(l), null));
    }
}
