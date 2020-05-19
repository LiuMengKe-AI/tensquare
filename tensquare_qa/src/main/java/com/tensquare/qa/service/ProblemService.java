package com.tensquare.qa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.qa.dao.ProblemMapper;
import com.tensquare.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.PageRequest;
import util.PageResult;
import util.PageUtils;

/**
 * @ClassName: ProblemService
 * @Author: LMK
 * @Date: 2020/5/19 13:07
 * @Version: 1.0
 **/
@Service
public class ProblemService {
    @Autowired
    ProblemMapper problemMapper;

    public PageResult findProblemList(String labelId, PageRequest pageRequest) {

        return PageUtils.getPageResult(pageRequest, getPageInfo(labelId, pageRequest));
    }

    private PageInfo<Problem> getPageInfo(String labelId, PageRequest pageRequest) {
        int pageNumber = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNumber, pageSize);
        Page<Problem> page = problemMapper.findByIdProblem(labelId);
        return new PageInfo<Problem>(page);
    }
}
