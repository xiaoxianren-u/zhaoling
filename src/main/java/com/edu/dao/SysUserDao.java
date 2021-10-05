package com.edu.dao;

import com.edu.pojo.User;
import org.springframework.stereotype.Component;

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


}
