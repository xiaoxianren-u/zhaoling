package com.edu.service;

import com.edu.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/29 15:41 星期三
 * @file : SysRoleService.java
 */

@Repository
public interface SysRoleService {


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
     * 新增角色
     *
     * @param role
     * @return
     */
    int add(Role role);
}
