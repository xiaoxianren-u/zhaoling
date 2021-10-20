package com.edu.service;

import com.edu.pojo.LogBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/20 15:35 星期三
 * @file : SysLogBookService.java
 */

/**
 * 日志管理
 *
 * @author yangzhan
 */
@Repository
public interface SysLogBookService {

    /**
     * 登录日志
     *
     * @param logBook
     */
    void logInsert(LogBook logBook);

    /**
     * 日志列表
     *
     * @param t
     * @param page
     * @param limit
     * @return
     */

    List<LogBook> selectList(Integer t, Integer page, Integer limit);

    /**
     * 相应日志数量
     *
     * @param t
     * @return
     */

    int selectCount(Integer t);
}
