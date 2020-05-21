package com.tensquare.qa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.IdWorker;

/**
 * @ClassName: QaApplication
 * @Author: LMK
 * @Date: 2020/5/18 13:32
 * @Version: 1.0
 **/
@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.tensquare.qa.dao", "mapper"})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class QaApplication {


    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
