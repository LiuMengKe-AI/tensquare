package com.tensquare.article.controller;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ArticleController
 * @Author: LMK
 * @Date: 2020/5/20 11:10
 * @Version: 1.0
 **/
@Api(tags = "ArticleController", description = "文章操作类")
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 审核文章状态
     */
    @ApiOperation(value = "审核文章状态")
    @GetMapping(value = "/updateStatus/{id}")
    @ResponseBody
    public Result examine(@PathVariable String id) {
        articleService.examine(id);
        return new Result(true, StatusCode.OK, "审核成功");
    }

    /**
     * 文章点赞
     */
    @ApiOperation(value = "文章点赞")
    @GetMapping(value = "/updateThumbup/{id}")
    @ResponseBody
    public Result updateThumbup(@PathVariable String id) {
        articleService.updateThumbup(id);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    /**
     * 查询文章并作缓存
     */
    @ApiOperation(value = "查询文章并作缓存")
    @GetMapping(value = "/findArticle/{id}")
    @ResponseBody
    public Result findArticle(@PathVariable String id) {
        Article article = articleService.findArticleById(id);
        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    /**
     * 删除文章同时删除redis
     */
    @ApiOperation(value = "删除文章同时删除redis")
    @GetMapping(value = "/deleteArticle/{id}")
    @ResponseBody
    public Result deleteArticle(@PathVariable String id) {
        if (id != null) {
            articleService.deleteArticleById(id);
            return new Result(true, StatusCode.OK, "删除成功");
        }
        return new Result(false, StatusCode.ERROR, "id不存在");
    }
}
