package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName: RecruitDao
 * @Author: LMK
 * @Date: 2020/5/16 16:11
 * @Version: 1.0
 **/
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {


    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String s);

    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String s);


}
