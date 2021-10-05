package com.edu.service.impl;

import com.edu.dao.SysRoleDao;
import com.edu.pojo.Role;
import com.edu.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/29 15:42 星期三
 * @file : SysRoleServiceImpl.java
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    /**
     * 角色管理列表
     *
     * @return
     */
    @Override
    public List<Role> listRole() {
        return sysRoleDao.listRole();
    }

    /**
     * 角色管理修改
     *
     * @param role
     * @return
     */
    @Override
    public int edit(Role role) {
        return sysRoleDao.edit(role);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public int dle(Integer roleId) {
        return sysRoleDao.dle(roleId);
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public int add(Role role) {
        role.setRo_number(sysRoleDao.selectNumber() + 1);
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        return sysRoleDao.add(role);
    }
}
