package com.tensquare.base.controller;

import com.github.pagehelper.PageInfo;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: UserController
 * @Author: LMK
 * @Date: 2020/5/13 16:46
 * @Version: 1.0
 **/
@Api(tags = "LabelController", description = "标签操作类")
@RequestMapping("/label")
@RestController
public class LabelController {
    @Autowired
    LabelService labelService;

    /**
     * 查询所有标签
     *
     * @return
     */
    @ApiOperation(value = "查询所有标签")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public Result<List> findAll() {
//        int a = 1/0;
        List<Label> labels = labelService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", labels);
    }

    /**
     * 根据id查询标签
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询标签")
    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    public Result<Label> findById(@PathVariable String id) {

        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * 添加标签
     *
     * @param label
     * @return
     */
    @ApiOperation(value = "添加标签")
    @PostMapping(value = "/save")
    @ResponseBody
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");

    }

    /**
     * 修改标签
     *
     * @param label
     * @param id
     * @return
     */
    @ApiOperation(value = "修改标签")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除标签")
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    @ApiOperation(value = "分页查询")
    @ResponseBody
    @PostMapping(value = "/searchPage")
    public Result<Label> findSearch(@RequestBody PageRequest pageRequest) {
        return new Result<Label>(true, StatusCode.OK, "查询成功", labelService.findPage(pageRequest));
    }
}
