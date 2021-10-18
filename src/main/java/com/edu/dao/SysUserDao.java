package com.edu.dao;

import com.edu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/2 15:27 星期六
 * @file : SysUserDao.java
 */
@Component
public interface SysUserDao {


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
    int selectRec(@Param("username") String username);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param date
     * @return
     */
    int insertRegister(@Param("username") String username, @Param("password") String password, @Param("date") Date date, @Param("home") String home);


    /**
     * 用户或管理员登录
     */

    int countUserAdmin(@Param("username") String username, @Param("password") String password);


    /**
     * 最后一次登录
     *
     * @param username
     * @param date
     * @return
     */
    int updateLogin(@Param("username") String username, @Param("date") Date date);

    /**
     * 查询角色类型是普通用户还是管理员
     *
     * @param username
     * @return
     */
    String selectStatus(String username);


    /**
     * 用户列表实现分页
     *
     * @param page
     * @param limit
     * @return
     */
    List<User> selectUserList(@Param("page") Integer page, @Param("limit") Integer limit);

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
    List<User> selectBlackList(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 用户黑名单列表
     *
     * @return
     */
    int selectblackCount();
}
