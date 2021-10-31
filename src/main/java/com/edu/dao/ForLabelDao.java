package com.edu.dao;

import com.edu.pojo.Label;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/31 18:24 星期日
 * @file : ForLabelDao.java
 */

@Component
public interface ForLabelDao {


    /**
     * 获取没有被禁的帖子标签
     *
     * @return
     */
    List<Label> forList();
}
