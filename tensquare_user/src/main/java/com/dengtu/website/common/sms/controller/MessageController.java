package com.dengtu.website.common.sms.controller;


import com.dengtu.website.common.CommonResult;
import com.dengtu.website.common.ResultCodeEnum;
import com.dengtu.website.common.enums.SendAuthCodeType;
import com.dengtu.website.common.sms.service.AuthCodeService;
import com.dengtu.website.common.util.VerificationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Type MessageController.java
 * @Desc 短信服务接口
 */
@Slf4j
@Controller
@Api(tags = "MessageController", description = "短信服务管理")
@RequestMapping("/cms")
public class MessageController {
    @Autowired
    AuthCodeService authCodeService;

    /**
     * 下发短信验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation(value = "下发短信验证码")
    @GetMapping(value = "/authCode/{type}/{phone}")
    @ResponseBody
    public CommonResult sendAuthCodeInLogin(@PathVariable("type") @ApiParam(value = "发短信的类型") String type,
                                            @PathVariable("phone") @ApiParam(value = "手机号") String phone) {


        SendAuthCodeType sendAuthCodeType = SendAuthCodeType.getSendAuthCodeTypeByValue(type);
        if (null == sendAuthCodeType || !VerificationUtil.verificationMobile(phone)) {
            return CommonResult.failed(ResultCodeEnum.BAD_REQUEST);
        }
        ResultCodeEnum result = authCodeService.sendAuthCode(sendAuthCodeType, phone);
        if (result.equals(ResultCodeEnum.SUCCESS)) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed(result);
        }
    }

}
