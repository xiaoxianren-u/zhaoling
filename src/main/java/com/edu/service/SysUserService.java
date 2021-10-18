package com.edu.service;

import com.edu.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/2 15:28 星期六
 * @file : SysUserService.java
 */


@Repository
public interface SysUserService {


    /**
     * 管理员列表
     *
     * @return
     */
    List<User> selectAdminList();


    /**
     * 查询是否有该用户
     *
     * @param username
     * @return
     */
    int selectRec(String username);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param date
     * @return
     */
    int insertRegister(String username, String password, Date date, String home);


    /**
     * 用户或管理员登录
     *
     * @param username
     * @param password
     * @return
     */

    int countUserAdmin(String username, String password);

    /**
     * 最后一次登录
     *
     * @param username
     * @param date
     * @return
     */
    int updateLogin(String username, Date date);

    /**
     * 查询角色类型是普通用户还是管理员
     *
     * @param username
     * @return
     */
    String selectStatus(String username);

    /**
     * 用户列表
     *
     * @return
     */
    List<User> selectUserList(Integer page, Integer limit);

    /**
     * 普通用户数量
     *
     * @return
     */
    int selectCount();

    /**
     * 用户黑名单列表
     *
     * @param page
     * @param limit
     * @return
     */
    List<User> selectBlackList(Integer page, Integer limit);

    /**
     * 用户黑名单数量
     *
     * @return
     */
    int selectblackCount();
}
