package com.edu.dao;

import com.edu.pojo.Label;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 获取对应标签的图片
     *
     * @param lab_name
     * @return
     */
    String getImage(@Param("lab_name") String lab_name);
}
