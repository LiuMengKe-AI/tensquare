package com.tensquare.qa.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @ClassName: ${NAME}
 * @Author: LMK
 * @Date:  2020/5/19 13:55
 * @Version: 1.0
**/
@Data
public class Problem implements Serializable {
    /**
    * ID
    */
    private String id;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 创建日期
    */
    private Date createtime;

    /**
    * 修改日期
    */
    private Date updatetime;

    /**
    * 用户ID
    */
    private String userid;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 浏览量
    */
    private Long visits;

    /**
    * 点赞数
    */
    private Long thumbup;

    /**
    * 回复数
    */
    private Long reply;

    /**
    * 是否解决
    */
    private String solve;

    /**
    * 回复人昵称
    */
    private String replyname;

    /**
    * 回复日期
    */
    private Date replytime;

    private static final long serialVersionUID = 1L;
}