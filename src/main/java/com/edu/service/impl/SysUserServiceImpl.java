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
     * @param
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

    /**
     * 用户列表
     *
     * @return
     */
    @Override
    public List<User> selectUserList(Integer page, Integer limit) {
        return sysUserDao.selectUserList(page, limit);
    }

    /**
     * 普通用户数量
     *
     * @return
     */
    @Override
    public int selectCount() {
        return sysUserDao.selectCount();
    }

    /**
     * 用户黑名单列表
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<User> selectBlackList(Integer page, Integer limit) {
        return sysUserDao.selectBlackList(page, limit);
    }

    /**
     * 黑名单数量
     *
     * @return
     */
    @Override
    public int selectblackCount() {
        return sysUserDao.selectblackCount();
    }

    /**
     * 获取某位用户的信息
     *
     * @param username
     * @return
     */
    @Override
    public User selectBasic(String username) {
        return sysUserDao.selectBasic(username);
    }
}
