package com.tensquare.base.pojo;

import lombok.Data;

@Data
public class TbCity {
    /**
     * ID
     */
    private String id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 是否热门
     */
    private String ishot;
}

