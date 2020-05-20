package com.tensquare.article.service;

import java.util.concurrent.TimeUnit;

import com.tensquare.article.dao.ArticleMapper;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ArticleService
 * @Author: LMK
 * @Date: 2020/5/20 11:10
 * @Version: 1.0
 **/
@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    RedisTemplate redisTemplate;

    public void examine(String id) {
        articleMapper.examine(id);
    }

    public void updateThumbup(String id) {
        articleMapper.updateThumbup(id);
    }

    public Article findArticleById(String id) {
        //从缓存中查询
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        System.out.println("从redis查询");
        if (article == null) {
            article = articleMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set("article_" + id, article, 7, TimeUnit.DAYS);
            System.out.println("已经存入redis");
        }
        return article;
    }

    public void deleteArticleById(String id) {

        redisTemplate.delete("article_" + id);//删除缓存

        articleMapper.deleteByPrimaryKey(id);


    }
}
