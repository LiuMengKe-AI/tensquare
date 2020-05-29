package com.dengtu.website.dao;

import com.dengtu.website.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Author: LMK
 * @Date: 2020/5/6 17:54
 * @Version: 1.0
 **/
public interface UserMapper {

    List<User> selectAllByMobile(@Param("mobile") String phone);

    void insert(User user);

    User selectByPrimaryKey(Integer id);

    User  selectOneByMobile(@Param("mobile")String mobile);

    int updateByPrimaryKeySelective(User record);
}
