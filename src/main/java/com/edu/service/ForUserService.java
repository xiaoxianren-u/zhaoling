package com.edu.service;

import com.edu.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author yz
 * @data: 2021/10/31 19:34 星期日
 * @file : ForUserService.java
 */

@Repository
public interface ForUserService {


    /**
     * 获取用户信息
     *
     * @param tokenUserName
     * @return
     */
    User selectName(String tokenUserName);

    /**
     * 个人头像用户名
     *
     * @param username
     * @return
     */
    User selectImage(String username);
}
