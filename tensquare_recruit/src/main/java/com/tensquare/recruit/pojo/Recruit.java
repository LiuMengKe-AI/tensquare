package com.tensquare.recruit.pojo;

import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_recruit")
public class Recruit {
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 职位名称
     */
    private String jobname;

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 经验要求
     */
    private String condition;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 任职方式
     */
    private String type;

    /**
     * 办公地址
     */
    private String address;

    /**
     * 企业ID
     */
    private String eid;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 状态
     */
    private String state;

    /**
     * 网址
     */
    private String url;

    /**
     * 标签
     */
    private String label;

    /**
     * 职位描述
     */
    private String content1;

    /**
     * 职位要求
     */
    private String content2;
}

