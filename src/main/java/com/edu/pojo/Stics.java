package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/11/25 22:14 星期四
 * @file : StatiStics.java
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统计类
 *
 * @author yangzhan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stics {


    private Integer st_id;

    /**
     * 周网站访问量
     */
    private Integer access;

    /**
     * 网站访问总量
     */
    private Long visits;

    /**
     * 这个月活跃用户人数
     */
    private Integer tMonth;
    /**
     * 最近一个月活跃用户人数
     */
    private Integer lMonth;

}
