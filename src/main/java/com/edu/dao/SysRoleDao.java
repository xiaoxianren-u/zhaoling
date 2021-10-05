package com.edu.dao;

import com.edu.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/29 15:43 星期三
 * @file : SysRoleDao.java
 */

@Component
public interface SysRoleDao {


    /**
     * 角色管理列表
     *
     * @return
     */
    List<Role> listRole();

    /**
     * 角色管理修改
     *
     * @param role
     * @return
     */
    int edit(Role role);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    int dle(@Param("roId") Integer roleId);


    /**
     * 获取角色编号最大值
     *
     * @return
     */

    int selectNumber();

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    int add(Role role);


}
