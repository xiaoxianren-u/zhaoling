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

//--============================================


    /**
     * 添加标签
     */
    void addLo(@Param("label") String label);

    /**
     * 添加标签
     */
    void addPo(@Param("label") String label);

    /**
     * 添加标签
     */
    void addRe(@Param("label") String label);


    /**
     * 删除标签
     */
    void delLo(@Param("lab_name") String lab_name);

    /**
     * 删除标签
     */
    void delPo(@Param("lab_name") String lab_name);

    /**
     * 删除标签
     */
    void delRe(@Param("lab_name") String lab_name);


    /**
     * 修改标签
     */
    void upLo(@Param("lab_name") String lab_name, @Param("name") String name);


    /**
     * 修改标签
     */
    void upPo(@Param("lab_name") String lab_name, @Param("name") String name);


    /**
     * 修改标签
     */
    void upre(@Param("lab_name") String lab_name, @Param("name") String name);


    /**
     * 用户数量
     */
    int selUserCount();

    /**
     * 当月注册用户量
     *
     * @return
     */
    int selUserCountNe();

    /**
     * 用户量的统计
     *
     * @param tMonth
     * @param lMonth
     */
    void insertMonth(@Param("tMonth") Integer tMonth, @Param("lMonth") Integer lMonth);


    /**
     * 上月归还量
     */
    int selpostCount();

    /**
     * 上月总的帖子量
     *
     * @return
     */
    int selPostCountNe();

    /**
     * 帖子量的统计
     *
     * @param tPost
     * @param lPost
     */
    void updatePostMonth(@Param("tPost") Integer tPost, @Param("lPost") Integer lPost);


    /**
     * 上周物品丢失情况
     *
     * @return
     */
    List<Count> selXinqi();

    /**
     * 上周物品丢失情况
     *
     * @param count
     * @param lab_name
     * @param currSun
     */
    void insXinqi(@Param("count") int count, @Param("lab_name") String lab_name, @Param("currSun") String currSun);


    /**
     * 本周反馈量
     *
     * @return
     */
    int getFeedCount();

    /**
     * 全部反馈量
     *
     * @return
     */
    int getFeeCount();

    /**
     * 数据写入
     *
     * @param n
     * @param m
     */
    void insFeed(@Param("n") int n, @Param("m") int m);
}
