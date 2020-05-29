package com.dengtu.website.common.sms.service;

import com.dengtu.website.common.ResultCodeEnum;
import com.dengtu.website.common.enums.SendAuthCodeType;

/**
 * @ClassName: AuthCodeService
 * @Author: LMK
 * @Date: 2020/5/6 16:46
 * @Version: 1.0
 **/
public interface AuthCodeService {

    /**
     * 发送验证码
     * @param type
     * @param phone
     * @return
     */
    ResultCodeEnum sendAuthCode(SendAuthCodeType type, String phone);

    /**
     * 验证验证码
     * @param type
     * @param phone
     * @param code
     * @return
     */
    Boolean verifyAuthCode(SendAuthCodeType type, String phone, String code);
}
