package com.edu.dao;

import com.edu.pojo.LogBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/20 15:23 星期三
 * @file : SysLogBookDao.java
 */

@Component
public interface SysLogBookDao {


    /**
     * 登录日志
     *
     * @param logBook
     * @return
     */
    int logInsert(LogBook logBook);


    /**
     * 日志列表
     *
     * @param t
     * @param page
     * @param limit
     * @return
     */
    List<LogBook> selectList(@Param("t") Integer t, @Param("page") Integer page, @Param("limit") Integer limit,
                             @Param("log_user") String log_user, @Param("time") Date time, @Param("log_start") String log_start);

    /**
     * 相应日志数量
     *
     * @param t
     * @return
     */
    int selectCount(@Param("t") Integer t, @Param("log_user") String log_user, @Param("time") Date time, @Param("log_start") String log_start);

}
