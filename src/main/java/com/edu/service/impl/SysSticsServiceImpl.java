package com.edu.service.impl;

import com.edu.dao.SticsDao;
import com.edu.pojo.Lose;
import com.edu.pojo.Polyline;
import com.edu.pojo.ReturnSi;
import com.edu.pojo.Stics;
import com.edu.service.SysSticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/11/26 14:02 星期五
 * @file : SticsServiceImpl.java
 */

@Service
public class SysSticsServiceImpl implements SysSticsService {


    @Autowired
    private SticsDao sticsDao;


    /**
     * 查询访问量
     *
     * @return
     */
    @Override
    public Stics selectStics() {
        return sticsDao.selectStics();
    }


    /**
     * 获取数据表数据
     *
     * @return
     */
    @Override
    public List<Polyline> selectPolylines() {
        return sticsDao.selectPolylines();
    }

    /**
     * 每日物品情况
     */
    @Override
    public List<ReturnSi> selectReturnSi() {
        return sticsDao.selectReturnSi();
    }


    /**
     * '物品丢失情况'
     */

    @Override
    public List<Lose> selectLose() {
        return sticsDao.selectLose();
    }


}
