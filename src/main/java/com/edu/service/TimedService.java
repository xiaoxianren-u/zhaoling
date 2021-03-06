package com.edu.service;

import org.springframework.stereotype.Repository;

/**
 * @author yz
 * @data: 2021/11/25 22:30 星期四
 * @file : TimedService.java
 */

@Repository
public interface TimedService {

    /**
     * 访问数量增加
     */
    void timeAdd();

    /**
     * 每周星期天 23：59:59
     */
    void timeUp();

    /**
     * 修改饼图数据
     */
    void upLose();

    /**
     * 修改柱形数据
     */
    void upReturn();

    /**
     * 柱状图恢复默认
     */
    void reDefault();


    /**
     * 添加数据分析表标签
     */

    void add(String label);

    /**
     * 删除数据分析表标签
     */

    void dele(String label);

    /**
     * 修改数据分析表标签
     */

    void update(String label, String name);

    /**
     * 用户数量
     */

    void selUserCount();

    /**
     * 上月物品量
     */
    void selPostCount();

    /**
     * 上周物品丢失情况
     */
    void selXinqi();

    /**
     * 反馈量
     */
    void selFanKui();

}
