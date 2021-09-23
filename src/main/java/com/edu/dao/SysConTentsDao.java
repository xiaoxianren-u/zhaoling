package com.edu.dao;

import com.edu.pojo.ConTents;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 17:35 星期一
 * @file : SysController.java
 */

@Component
public interface SysConTentsDao {


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
