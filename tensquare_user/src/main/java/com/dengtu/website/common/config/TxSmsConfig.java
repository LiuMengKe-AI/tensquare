package com.dengtu.website.common.config;

/**
 * @ClassName: TxSmsConfig
 * @Author: LMK
 * @Date: 2020/5/6 17:13
 * @Version: 1.0
 **/
public class TxSmsConfig {
    // 短信应用 SDK AppID
    public final static int appid = 1400290542; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    public final static String appkey = "80fb0af1ba3c6d8179d783a2878462c0";
    // 需要发送短信的手机号码
//    private final String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
    //验证码 短信模板 ID，需要在短信应用中申请
    public final static int templateId1 = 524244; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 验证码 短信签名
    public final static String smsSign1 = "HASHCLUB"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
    //营销短信模板
    public final static int templateId2 = 524244;
    // 营销 短信签名
    public final static String smsSign2 = "HASHCLUB";
}
