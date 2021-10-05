package com.edu.service;

import com.edu.pojo.User;
import org.springframework.stereotype.Repository;

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
}
