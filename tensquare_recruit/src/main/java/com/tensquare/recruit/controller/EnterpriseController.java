package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.EnterpriseService;
import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: EnterpriseController
 * @Author: LMK
 * @Date: 2020/5/16 14:35
 * @Version: 1.0
 **/
@RestController
@Api(tags = "EnterpriseController", description = "热门企业操作类")
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;
@Autowired
    RecruitService recruitService;
    /**
     * 查询热门的公司
     * @return
     */
    @GetMapping(value = "/search/hotList")
    public Result<Enterprise> hotList() {
        List<Enterprise> list = enterpriseService.hotList();
        System.out.println(list);
        return new Result(true, StatusCode.OK, "查询成功",list);
    }

    /**
     * 查询最新职位列表(按创建日期降序排序)
     * @return
     */
    @GetMapping(value = "/search/recruit")
    public Result<Recruit> findRecruit(){
        return new Result<> (true,StatusCode.OK,"查询成功",recruitService.findTop4ByStateOrderByCreatetimeDesc("2"));
    }
}
