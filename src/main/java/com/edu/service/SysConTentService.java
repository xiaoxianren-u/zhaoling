package com.edu.service;

import com.edu.pojo.ConTents;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 17:40 星期一
 * @file : SysConTentService.java
 */
@Repository
public interface SysConTentService {


    /**
     * 菜单管理列表
     *
     * @return
     */
    List<ConTents> selectContents();

    /**
     * 后台导航栏
     *
     * @return
     */
    List<ConTents> selectContents_sys();

}
