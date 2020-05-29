package com.dengtu.website.common.sms.service;

/**
 * @ClassName: MTService
 * @Author: LMK
 * @Date: 2020/5/6 16:40
 * @Version: 1.0
 **/
public interface MTService {
    boolean sendMessage(String phone, String message);
}
