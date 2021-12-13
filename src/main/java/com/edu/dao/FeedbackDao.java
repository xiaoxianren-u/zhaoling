package com.edu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author yz
 * @data: 2021/12/13 22:19 星期一
 * @file : FeedbackDao.java
 */

/**
 * 意见反馈
 *
 * @author yangzhan
 */
@Repository
public interface FeedbackDao {


    /**
     * 添加意见反馈
     *
     * @param text
     * @param date
     */
    void feedBackAdd(@Param("text") String text, @Param("date") Date date);
}
