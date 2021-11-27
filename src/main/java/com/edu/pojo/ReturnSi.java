package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/11/26 22:10 星期五
 * @file : ReturnSi.java
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每日物品情况
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnSi {


    private Integer re_id;

    /**
     * 标签名
     */
    private String lab_name;

    /**
     * 丢失
     */
    private Integer re_lose;

    /**
     * 找回
     */
    private Integer re_return;


}
