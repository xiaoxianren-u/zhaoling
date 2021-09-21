package com.edu.dao;

import com.edu.pojo.ConTents;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 17:35 星期一
 * @file : SysController.java
 */


public interface SysConTentsDao {


    /**
     * 后台目录
     * @return
     */
    List<ConTents> selectContents();


}
