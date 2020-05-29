package com.dengtu.website.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Author: LMK
 * @Date: 2020/5/6 16:09
 * @Version: 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户管理主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态，1-正常 | 0-禁用
     */
    private Integer status;
    /**
     * 用户注册协议 0未同意 1同意
     */
    private Integer userRegister;
    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 用户的openid
     */
    private String openid;
}
