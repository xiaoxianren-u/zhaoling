package com.edu.service.impl;

import com.edu.dao.SysUserDao;
import com.edu.pojo.User;
import com.edu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/2 15:28 星期六
 * @file : SysUserServiceImpl.java
 */

@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 管理员列表
     *
     * @return
     */
    @Override
    public List<User> selectAdminList() {
        return sysUserDao.selectAdminList();
    }
}
