package com.edu.dao;

import com.edu.pojo.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/27 20:05 星期三
 * @file : SysLabelDao.java
 */

@Component
public interface SysLabelDao {

    /**
     * 标签列表
     *
     * @return
     */
    List<Label> list();

    /**
     * 删除帖子标签
     *
     * @param lab_id
     * @return
     */
    void del(@Param("lab_id") Integer lab_id);

    /**
     * 修改帖子标签
     *
     * @param label
     * @return
     */
    void up(Label label);

    /**
     * 添加帖子标签
     *
     * @param label
     * @return
     */
    void add(Label label);
}
