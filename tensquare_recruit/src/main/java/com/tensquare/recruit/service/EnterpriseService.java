package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: EnterpriseService
 * @Author: LMK
 * @Date: 2020/5/16 14:53
 * @Version: 1.0
 **/
@Service
public class EnterpriseService {
    @Autowired
    EnterpriseDao enterpriseDao;


    public List<Enterprise> hotList() {
        return enterpriseDao.findByIshot("1");

    }


}
