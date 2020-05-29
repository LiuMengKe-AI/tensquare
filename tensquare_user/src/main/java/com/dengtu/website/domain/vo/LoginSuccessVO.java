package com.dengtu.website.domain.vo;

import lombok.Data;

/**
 * @ClassName: LoginSuccessVO
 * @Author: LMK
 * @Date: 2020/5/7 13:31
 * @Version: 1.0
 **/
@Data
public class LoginSuccessVO {
    private String token;
    private String tokenHead;
    private UserInfoVO user;
}
