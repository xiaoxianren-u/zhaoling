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
     * 当月注册用户人数
     */
    private Integer tMonth;
    /**
     * 总人数
     */
    private Integer lMonth;

    /**
     * 上月归还的总量
     */
    private Integer tPost;

    /**
     * 上月发布的总量
     */
    private Integer lPost;


    /**
     *
     */
    private Integer tCount;

    /**
     *
     */
    private Integer lCount;


}
