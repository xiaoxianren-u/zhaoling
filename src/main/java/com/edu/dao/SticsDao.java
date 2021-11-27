package com.edu.dao;

import com.edu.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/11/25 23:36 星期四
 * @file : SticsDao.java
 */

@Component
public interface SticsDao {


    /**
     * 查询访问量
     *
     * @return
     */
    Stics selectStics();

    /**
     * 访问数量增加
     *
     * @param count
     */
    void accessAdd(@Param("count") int count);


    /**
     * 每周星期天 23：59:59
     */
    void accessUp();

    /**
     * 查询所有数据表的数据
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

    /**
     * 查询某个月的发帖数量 既：丢失和寻找数量
     */
    List<Count> selCount();

    /**
     * 修改饼图的数据
     *
     * @param s
     */
    void upLose(@Param("s") Count s);


    /**
     * 获取柱状图丢失
     */

    List<Count> selZhu();

    /**
     * 修改柱状图的丢失数据
     */
    void upReLose(Count s);

    /**
     * 获取柱状图找回
     */

    List<Count> selRe();

    /**
     * 修改柱状图的找回数据
     */
    void upRe(Count s);

    /**
     * 柱状图恢复默认
     */
    void reDefault();


}
