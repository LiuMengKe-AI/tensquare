package com.tensquare.base.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName: ${NAME}
 * @Author: LMK
 * @Date: 2020/5/18 16:14
 * @Version: 1.0
 **/
@Data
public class Label implements Serializable {
    /**
     * 标签ID
     */
    private String id;

    /**
     * 标签名称
     */
    private String labelname;

    /**
     * 状态
     */
    private String state;

    /**
     * 使用数量
     */
    private Long count;

    /**
     * 是否推荐
     */
    private String recommend;

    /**
     * 粉丝数
     */
    private Long fans;

    private static final long serialVersionUID = 1L;
}