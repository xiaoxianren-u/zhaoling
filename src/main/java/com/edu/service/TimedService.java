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

}
