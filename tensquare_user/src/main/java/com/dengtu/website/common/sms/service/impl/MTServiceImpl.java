package com.dengtu.website.common.sms.service.impl;

import cn.hutool.json.JSONException;
import com.dengtu.website.common.config.TxSmsConfig;
import com.dengtu.website.common.sms.service.MTService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName: MTServiceImpl
 * @Author: LMK
 * @Date: 2020/5/6 16:42
 * @Version: 1.0
 **/
@Slf4j
@Service
public class MTServiceImpl implements MTService {

    @Override
    public boolean sendMessage(String phone, String message) {
        try {
            String[] params = {message};
            SmsSingleSender ssender = new SmsSingleSender(TxSmsConfig.appid, TxSmsConfig.appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, TxSmsConfig.templateId1, params, TxSmsConfig.smsSign1, "", "");
            log.info("腾讯短信发送返回结果:" + result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
            log.info("错误信息:{}" + e.getMessage());
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
            log.info("错误信息:{}" + e.getMessage());
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
            log.info("错误信息:{}" + e.getMessage());
        }
        return true;
    }
}

