package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RecruitService
 * @Author: LMK
 * @Date: 2020/5/16 16:13
 * @Version: 1.0
 **/
@Service
public class RecruitService {
    @Autowired
    RecruitDao recruitDao;

    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String s) {
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc(s);
    }
}
