package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: LabelClient
 * @Author: LMK
 * @Date: 2020/5/21 13:48
 * @Version: 1.0
 **/
@FeignClient(value = "tensquare-base")  //@FeignClient注解用于指定从哪个服务中调用功能
public interface LabelClient {

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable(value = "id") String labelId);
}
