package com.dengtu.website.common.sms.service.impl;

import com.dengtu.website.common.ResultCodeEnum;
import com.dengtu.website.common.enums.SendAuthCodeType;
import com.dengtu.website.common.error.PlatformException;
import com.dengtu.website.common.sms.service.AuthCodeService;
import com.dengtu.website.common.sms.service.MTService;
import com.dengtu.website.domain.User;
import com.dengtu.website.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AuthCodeServiceImpl
 * @Author: LMK
 * @Date: 2020/5/6 16:46
 * @Version: 1.0
 **/
@Slf4j
@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    public static final String AUTH_CODE_KEY_PREFIX = "authcode:";
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MTService mtService;

    @Autowired
    private UserService userService;

    /**
     * 验证码失效时间
     */
    private static final int AUTHCODE_EXPIRE_DATE = 15;

    /**
     * 发送验证码
     * @param type
     * @param phone
     * @return
     */
    @Override
    public ResultCodeEnum sendAuthCode(SendAuthCodeType type, String phone) {
        //忘记密码时，需要校验是否存在用户有该手机号码
        if (SendAuthCodeType.RESET_PASSWORD.getId().equals(type.getId())) {
            List<User> list = userService.getUserListByPhone(phone);
            if (ObjectUtils.isEmpty(list)) {
                return ResultCodeEnum.PHONE_TENANT_ERROR;
            }
        }
        //注册时候发送的验证码
        if (SendAuthCodeType.REGISTER.getId().equals(type.getId())) {
            List<User> list = userService.getUserListByPhone(phone);
            if (list.size() > 0) {
                return ResultCodeEnum.PHONE_EXIST;
            }
        }

        String key = AUTH_CODE_KEY_PREFIX + type.getValue().toLowerCase() + ":" + phone;
        String value = (String) redisTemplate.opsForValue().get(key);

        //验证码未超有效期
        if (!StringUtils.isEmpty(value)) {
            return ResultCodeEnum.AUTHCODE_EXPIRE_ERROR;
        }

        //发送验证码
        String code = RandomStringUtils.randomNumeric(4);
        log.info("手机号为" + phone + "的验证码为:{}", code);
        //if(mtService.sendMessage(phone, code)) {
        if (true) {
            redisTemplate.opsForValue().set(key, code);
            redisTemplate.expire(key, AUTHCODE_EXPIRE_DATE, TimeUnit.MINUTES);
            return ResultCodeEnum.SUCCESS;
        } else {

            return ResultCodeEnum.AUTHCODE_SEND_ERROR;
        }
    }

    /**
     * 验证验证码
     * @param type
     * @param phone
     * @param code
     * @return
     */
    @Override
    public Boolean verifyAuthCode(SendAuthCodeType type, String phone, String code) {
        String key = AUTH_CODE_KEY_PREFIX + type.getValue().toLowerCase() + ":" + phone;
        String value = (String) redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(value)) {
            if (value.equals(code)) {
                //redisTemplate.delete(key);
                return true;
            }
            log.error(phone + "输入的验证码" + code + "不正确");
        } else {
            log.error(phone + "找不到对应的验证码，验证码可能失效了");
        }
        throw new PlatformException(ResultCodeEnum.TENANT_AUTHCODE_ERROR);
    }
}
