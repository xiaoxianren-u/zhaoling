package com.edu.dao;

import com.edu.pojo.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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


    /**
     * 意见反馈数量
     *
     * @return
     */
    int count();

    /**
     * 意见反馈表
     *
     * @param page
     * @param limit
     * @return
     */
    List<Feedback> sel(@Param("page") Integer page, @Param("limit") Integer limit);


    /**
     * 删除意见反馈
     *
     * @param id
     */
    void del(Integer id);
}
