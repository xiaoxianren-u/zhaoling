package com.edu.service.impl;

import com.edu.dao.ForUserDao;
import com.edu.pojo.User;
import com.edu.service.ForUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yz
 * @data: 2021/10/31 19:34 星期日
 * @file : ForUserServiceImpl.java
 */

@Service
public class ForUserServiceImpl implements ForUserService {


    @Autowired
    private ForUserDao forUserDao;

    @Override
    public User selectName(String tokenUserName) {
        return forUserDao.selectName(tokenUserName);
    }

    /**
     * 个人头像用户名
     *
     * @param username
     * @return
     */
    @Override
    public User selectImage(String username) {
        return forUserDao.selectImage(username);
    }

}
