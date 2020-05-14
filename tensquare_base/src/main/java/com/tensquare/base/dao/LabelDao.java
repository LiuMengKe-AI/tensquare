package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: LabelDao
 * @Author: LMK
 * @Date: 2020/5/13 16:10
 * @Version: 1.0
 **/
public interface LabelDao extends JpaRepository<Label,String> ,JpaSpecificationExecutor<Label> {

}
