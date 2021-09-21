package com.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yz
 * @data: 2021/9/21 15:12 星期二
 * @file : Label.java
 */

/**
 * 标签表
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Label {

    /**
     * 标签id
     */
    private Integer lab_id;
    /**
     * 标签名
     */
    private String lab_name;

}
