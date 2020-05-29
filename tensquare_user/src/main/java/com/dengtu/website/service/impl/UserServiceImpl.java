package com.dengtu.website.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.dengtu.website.common.error.PlatformException;
import com.dengtu.website.common.security.component.MyUserDetailsService;
import com.dengtu.website.common.security.util.JwtTokenUtil;
import com.dengtu.website.common.sms.config.SmsCodeAuthenticationToken;
import com.dengtu.website.common.util.SecurityUtil;
import com.dengtu.website.common.util.VerificationUtil;
import com.dengtu.website.dao.UserMapper;
import com.dengtu.website.domain.User;
import com.dengtu.website.domain.dto.UserRegisterParam;
import com.dengtu.website.domain.vo.UserInfoVO;
import com.dengtu.website.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Author: LMK
 * @Date: 2020/5/6 16:44
 * @Version: 1.0
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public User selectOneByPhone(String username) {
        return userMapper.selectOneByMobile(username);
    }

    @Override
    public User register(UserRegisterParam param) {
        List<User> users = userMapper.selectAllByMobile(param.getPhone());
        if (users.size() > 0) {
            return null;
        }
        User user = new User();
        user.setMobile(param.getPhone());
        int time = Long.valueOf(System.currentTimeMillis() / 1000).intValue();
        user.setCreateTime(time);
        user.setUpdateTime(time);
        user.setStatus(1);//状态，1-正常 | 0-禁用
        if (!VerificationUtil.verificationPassword(param.getPassword())) {
            throw new PlatformException(400, "密码格式不正确");
        }
        user.setPassword(passwordEncoder.encode(param.getPassword()));
        user.setUsername(param.getPhone());
        user.setUserRegister(1); //0未同意 1同意
        userMapper.insert(user);
        return user;
    }

    @Override
    public List<User> getUserListByPhone(String phone) {
        return userMapper.selectAllByMobile(phone);
    }

    @Override
    public String login(String username, String password) {
        //密码需要加密传给后台
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            log.info("登录密码错误");
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserInfoVO getUserInfo() {
        Integer id = SecurityUtil.getUserId();
        User user = userMapper.selectByPrimaryKey(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserName(user.getUsername());
        userInfoVO.setPhone(user.getMobile());
        userInfoVO.setState(user.getStatus());
        return userInfoVO;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public Integer updateByPhone(UserRegisterParam param) {
        User user = userMapper.selectOneByMobile(param.getPhone());
        if (ObjectUtil.isEmpty(user)) {
            return 0;
        }
        //加密密码存入数据库
        user.setPassword(passwordEncoder.encode(param.getPassword()));
        return updateByPrimaryKeySelective(user);
    }
    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public String smsLogin(String phone, String code) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(phone);

        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return jwtTokenUtil.generateToken(userDetails);
    }
}
