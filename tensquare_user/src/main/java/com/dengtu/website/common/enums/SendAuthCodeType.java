package com.dengtu.website.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Type SendAuthCodeType.java
 * @Desc 发送短信验证码类型
 */
@Getter
@AllArgsConstructor
public enum SendAuthCodeType {
    LOGIN(0, "登录", "login"),

    RESET_PASSWORD(1, "忘记密码", "resetPwd"),

    REGISTER(2, "用户注册", "register");



    private Integer id;
    private String desc;
    private String value;

    public static SendAuthCodeType getSendAuthCodeTypeByValue(String value) {
        for (SendAuthCodeType type : SendAuthCodeType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
    }
