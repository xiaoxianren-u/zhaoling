package com.edu.service.impl;

import com.edu.dao.SysLogBookDao;
import com.edu.pojo.LogBook;
import com.edu.service.SysLogBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/20 15:37 星期三
 * @file : SysLogBookServiceImpl.java
 */

/**
 * 日志管理
 *
 * @author yangzhan
 */

@Service
public class SysLogBookServiceImpl implements SysLogBookService {

    @Autowired
    private SysLogBookDao sysLogBookDao;


    /**
     * 日志管理
     *
     * @param logBook
     */
    @Override
    public void logInsert(LogBook logBook) {
        sysLogBookDao.logInsert(logBook);
    }

    /**
     * 日志列表
     *
     * @param t
     * @param page
     * @param limit
     * @return
     */

    @Override
    public List<LogBook> selectList(Integer t, Integer page, Integer limit) {
        return sysLogBookDao.selectList(t, page, limit);
    }

    /**
     * 相应日志数量
     *
     * @param t
     * @return
     */
    @Override
    public int selectCount(Integer t) {
        return sysLogBookDao.selectCount(t);
    }
}
