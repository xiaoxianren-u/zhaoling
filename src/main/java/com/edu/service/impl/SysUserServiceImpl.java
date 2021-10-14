package com.edu.service.impl;

import com.edu.dao.SysUserDao;
import com.edu.pojo.User;
import com.edu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    /**
     * 查询是否有该用户
     *
     * @param username
     * @return
     */
    @Override
    public int selectRec(String username) {
        return sysUserDao.selectRec(username);
    }

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param date
     * @return
     */
    @Override
    public int insertRegister(String username, String password, Date date, String home) {
        return sysUserDao.insertRegister(username, password, date, home);
    }

    /**
     * 用户或管理员登录
     *
     * @param username
     * @param password
     * @param date
     * @return
     */
    @Override
    public int countUserAdmin(String username, String password) {
        return sysUserDao.countUserAdmin(username, password);
    }

    /**
     * 最后一次登录
     *
     * @param username
     * @param date
     * @return
     */
    @Override
    public int updateLogin(String username, Date date) {
        return sysUserDao.updateLogin(username, date);
    }

    /**
     * 查询角色类型是普通用户还是管理员
     *
     * @param username
     * @return
     */
    @Override
    public String selectStatus(String username) {
        return sysUserDao.selectStatus(username);
    }
}
