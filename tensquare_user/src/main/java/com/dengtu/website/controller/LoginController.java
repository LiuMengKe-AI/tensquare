package com.dengtu.website.controller;

import cn.hutool.core.date.DateUtil;
import com.dengtu.website.common.CommonResult;
import com.dengtu.website.common.enums.SendAuthCodeType;
import com.dengtu.website.common.sms.service.AuthCodeService;
import com.dengtu.website.common.sms.service.MTService;
import com.dengtu.website.domain.User;
import com.dengtu.website.domain.dto.UserLoginParam;
import com.dengtu.website.domain.dto.UserRegisterParam;
import com.dengtu.website.domain.vo.LoginSuccessVO;
import com.dengtu.website.domain.vo.UserInfoVO;
import com.dengtu.website.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Author: LMK
 * @Date: 2020/5/6 16:38
 * @Version: 1.0
 **/
@RestController
@Api(tags = "LoginController", description = "用户管理")
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AuthCodeService authCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private MTService mtService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody UserRegisterParam param) {
        if (authCodeService.verifyAuthCode(SendAuthCodeType.REGISTER, param.getPhone(), param.getVcode())) {
            User umsAdmin = userService.register(param);
            if (umsAdmin == null) {
                return CommonResult.failed(400, "手机号已存在请直接登录");
            }
            return CommonResult.success();
        }


        return CommonResult.failed();
    }

    /**
     * 手机号密码登录
     * @param loginParam
     * @return
     */
    @ApiOperation(value = "账户密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginSuccessVO> login(@RequestBody @Valid UserLoginParam loginParam) {
        String token = userService.login(loginParam.getUsername(), loginParam.getPassword());
        if (token == null) {
            return CommonResult.failed("用户名或密码错误");
        }
        LoginSuccessVO vo = new LoginSuccessVO();
        vo.setToken(token);
        vo.setTokenHead(tokenHead);
        UserInfoVO userInfoVO = userService.getUserInfo();
        vo.setUser(userInfoVO);
        return CommonResult.success(vo);
    }

    /**
     * 刷新token
     * @param request
     * @return
     */
    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ROLE')")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }


        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }



    @ApiOperation(value = "忘记密码")
    @PostMapping(value = "/forgetPwd")
    @ResponseBody
    public CommonResult forgetPwd(@RequestBody UserRegisterParam param) {
        if (authCodeService.verifyAuthCode(SendAuthCodeType.RESET_PASSWORD, param.getPhone(), param.getVcode())) {
            Integer integer = userService.updateByPhone(param);
            if (integer == 0) {
                return CommonResult.failed(400, "用户不存在请先注册");
            }
            return CommonResult.success();
        }
        return CommonResult.failed();
    }


    @ApiOperation(value = "手机验证码登录")
    @RequestMapping(value = "/sms/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginSuccessVO> smsLogin(String phone, String code) {
        if (authCodeService.verifyAuthCode(SendAuthCodeType.LOGIN, phone, code)) { //验证登录的手机号和验证码
            String token = userService.smsLogin(phone, code);
            LoginSuccessVO vo = new LoginSuccessVO();
            vo.setToken(token);
            vo.setTokenHead(tokenHead);
            UserInfoVO userInfoVO = userService.getUserInfo();
            vo.setUser(userInfoVO);
            return CommonResult.success(vo);
        }
        return CommonResult.failed();
    }
    /**
     * 退出
     * @return
     */
    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        //  Integer id = SecurityUtil.getUserId();
        log.info("[退出登录成功]-[{}]", DateUtil.date());
        return CommonResult.success(null);
    }
}
