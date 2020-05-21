package com.tensquare.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.IdWorker;

/**
 * @ClassName: com.tensquare.base.BaseApplication
 * @Author: LMK
 * @Date: 2020/5/13 15:16
 * @Version: 1.0
 **/
@EnableSwagger2
@MapperScan({"com.tensquare.base.dao", "mapper"})
@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {


    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
