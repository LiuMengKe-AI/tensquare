package com.tensquare.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.IdWorker;

/**
 * @ClassName: ArticleApplication
 * @Author: LMK
 * @Date: 2020/5/18 13:32
 * @Version: 1.0
 **/
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.tensquare.article.dao","mapper"})
public class ArticleApplication {



    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
