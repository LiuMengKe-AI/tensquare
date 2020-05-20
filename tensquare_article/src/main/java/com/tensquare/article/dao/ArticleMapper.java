package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ${NAME}
 * @Author: LMK
 * @Date:  2020/5/20 11:07
 * @Version: 1.0
**/
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    void examine(String id);

    void updateThumbup(String id);
}