package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/9/20 16:22 星期一
 * @file : ConTents.java
 */

import lombok.Data;

/**
 *
 *后台导航栏目录
 * @author yangzhan
 */

@Data
public class ConTents {

    /**
     * 目录id
     */
    private Integer con_id;
    /**
     * 目录名字
     */
    private String con_name;
    /**
     * 目录链接
     */
    private String con_url;
    /**
     * 目录序号
     */
    private Integer con_number;
    /**
     * 目录图标
     */
    private String con_icon;

}
