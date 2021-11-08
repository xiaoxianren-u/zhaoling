package com.edu.service;

import com.edu.pojo.User;
import com.edu.util.AjaxUtils;
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
    List<User> selectAdminList(Integer page, Integer limit, String username);

    /**
     * 管理员数量
     *
     * @param username
     * @return
     */
    int adminCount(String username);


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
    List<User> selectUserList(Integer page, Integer limit, String username);

    /**
     * 普通用户数量
     *
     * @return
     */
    int selectCount(String username);

    /**
     * 用户黑名单列表
     *
     * @param page
     * @param limit
     * @return
     */
    List<User> selectBlackList(Integer page, Integer limit, String username);

    /**
     * 用户黑名单数量
     *
     * @return
     */
    int selectblackCount(String username);

    /**
     * 获取某位用户的信息
     *
     * @param username
     * @return
     */
    User selectBasic(String username);

    /**
     * 基本信息修改
     *
     * @param user
     */
    void updateBasic(User user);

    /**
     * 获取密码
     *
     * @param user_name
     * @return
     */
    String selectPassword(String user_name);

    /**
     * 更改密码
     *
     * @param name
     */
    void updatePass(String username, String name);


    /**
     * 修改用户权限或拉黑
     *
     * @param user
     * @return
     */
    AjaxUtils upUser(User user);

    /**
     * 对现有的管理员处理
     *
     * @param user
     * @return
     */
    AjaxUtils upUserNot(User user);

    /**
     * 修改黑名单权限或拉黑
     *
     * @param user
     * @return
     */
    AjaxUtils upUserBlack(User user);

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    AjaxUtils dlUser(User user);


}
