package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_pl")
public class Pl implements Serializable {
    /**
     * 问题ID
     */
    @Id
    private String problemid;

    /**
     * 标签ID
     */
    @Id
    private String labelid;
}

