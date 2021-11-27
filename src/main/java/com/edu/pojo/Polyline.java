package com.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yz
 * @data: 2021/11/26 20:28 星期五
 * @file : Polyline.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Polyline {

    private Integer pol_id;

    /**
     * 标签名
     */
    private String lab_name;
    /**
     * 星期一
     */
    private Integer monday;
    /**
     * 星期二
     */
    private Integer tuesday;
    /**
     * 星期三
     */
    private Integer wednesday;
    /**
     * 星期四
     */
    private Integer thursday;
    /**
     * 星期五
     */
    private Integer friday;
    /**
     * 星期六
     */
    private Integer saturday;
    /**
     * 星期天
     */
    private Integer sunday;

}
