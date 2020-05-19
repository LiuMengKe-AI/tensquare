package com.tensquare.qa.dao;

import com.github.pagehelper.Page;
import com.tensquare.qa.pojo.Problem;

/**
 * @ClassName: ${NAME}
 * @Author: LMK
 * @Date:  2020/5/19 13:55
 * @Version: 1.0
**/
public interface ProblemMapper {
    int deleteByPrimaryKey(String id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);
    Page<Problem> findByIdProblem(String labelId);
}