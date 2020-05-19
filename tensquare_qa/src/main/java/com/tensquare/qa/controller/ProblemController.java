package com.tensquare.qa.controller;

import com.tensquare.qa.service.ProblemService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.PageRequest;

/**
 * @ClassName: ProblemController
 * @Author: LMK
 * @Date: 2020/5/19 11:24
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/problem")
@Api(tags = "ProblemController", description = "招聘微服务操作类")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    /**
     * 根据标签id查询最新问题列表
     */
    @ApiOperation(value = "根据标签id查询最新问题列表")
    @GetMapping(value = "/findProblem/{labelId}")
    @ResponseBody
    public Result findByIdProblem(@PathVariable String labelId,  PageRequest pageRequest) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findProblemList(labelId, pageRequest));
    }

}