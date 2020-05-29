package com.dengtu.website.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ResultCodeEnum
 * @Author: LMK
 * @Date: 2020/5/6 16:23
 * @Version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数检验失败"),
    AUTHCODE_EXPIRE_ERROR(407, "验证码仍在有效期内"),
    AUTHCODE_SEND_ERROR(408, "验证码下发失败，请重试"),
    PASSWORD_ERROR(409,"两次支付密码不一样/支付密码的长度不对"),
    PHONE_EXIST(410, "手机号已存在"),
    PHONE_TENANT_ERROR(411, "手机号错误/未注册"),
    TENANT_AUTHCODE_ERROR(412, "验证码错误"),
    FAILED(500, "操作失败");
    private Integer code;
    private String message;
}
