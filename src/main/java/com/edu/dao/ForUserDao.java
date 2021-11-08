package com.edu.dao;

import com.edu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author yz
 * @data: 2021/10/31 19:34 星期日
 * @file : ForUserDao.java
 */

@Component
public interface ForUserDao {


    /**
     * 获取用户信息
     *
     * @param tokenUserName
     * @return
     */
    User selectName(@Param("username") String tokenUserName);

    /**
     * 个人头像用户名
     *
     * @param username
     * @return
     */
    User selectImage(String username);
}
