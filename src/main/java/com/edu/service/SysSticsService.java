package com.edu.service;

import com.edu.pojo.Lose;
import com.edu.pojo.Polyline;
import com.edu.pojo.ReturnSi;
import com.edu.pojo.Stics;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/11/26 14:02 星期五
 * @file : SticsService.java
 */
@Repository
public interface SysSticsService {

    /**
     * 访问量
     *
     * @return
     */
    Stics selectStics();

    /**
     * 获取数据表数据
     *
     * @return
     */
    List<Polyline> selectPolylines();


    /**
     * 每日物品情况
     */
    List<ReturnSi> selectReturnSi();


    /**
     * '物品丢失情况'
     */
    List<Lose> selectLose();


}
