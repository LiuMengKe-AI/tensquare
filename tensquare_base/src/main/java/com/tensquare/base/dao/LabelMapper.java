package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.apache.ibatis.annotations.Mapper;import java.util.List;import java.util.Optional;

/**
 * @ClassName: ${NAME}
 * @Author: LMK
 * @Date: 2020/5/18 16:14
 * @Version: 1.0
 **/
public interface LabelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    List<Label> findAll();

    List<Label> findPages();
//
//    Optional<Label> findById(String id);
//
//    void save(Label label);
//
//    void deleteById(String id);
}