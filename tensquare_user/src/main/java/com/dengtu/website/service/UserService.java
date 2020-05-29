package com.dengtu.website.service;

import com.dengtu.website.domain.User;
import com.dengtu.website.domain.dto.UserRegisterParam;
import com.dengtu.website.domain.vo.UserInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserService
 * @Author: LMK
 * @Date: 2020/5/6 16:04
 * @Version: 1.0
 **/

public interface UserService {


    User selectOneByPhone(String username);

    /**
     * 用户注册
     * @param param
     * @return
     */
    User register(UserRegisterParam param);

    /**
     * 通过手机号获取用户信息
     * @param phone
     * @return
     */
    List<User> getUserListByPhone(String phone);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    UserInfoVO getUserInfo();

    String refreshToken(String token);

    Integer updateByPhone(UserRegisterParam param);
    int updateByPrimaryKeySelective(User record);

    /**
     * 手机验证码登录
     * @param phone
     * @param code
     * @return
     */
    String smsLogin(String phone, String code);
}
