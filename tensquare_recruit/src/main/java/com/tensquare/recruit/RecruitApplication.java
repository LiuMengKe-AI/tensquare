package com.tensquare.recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.IdWorker;

/**
 * @ClassName: RecruitApplication
 * @Author: LMK
 * @Date: 2020/5/16 14:27
 * @Version: 1.0
 **/
@EnableSwagger2
@SpringBootApplication
//@MapperScan({"com.tensquare.recruit.dao", "mapper"})
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
