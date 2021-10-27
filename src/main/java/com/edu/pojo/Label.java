package com.edu.pojo;

import lombok.*;

/**
 * @author yz
 * @data: 2021/9/21 15:12 星期二
 * @file : Label.java
 */

/**
 * 标签表
 */

@ToString
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

    /**
     * 标签状态 0启用，1禁止
     */
    private Integer lab_status;


}
