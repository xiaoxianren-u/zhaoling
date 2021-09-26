package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/9/20 16:22 星期一
 * @file : ConTents.java
 */

import lombok.*;

/**
 * 后台导航栏目录
 *
 * @author yangzhan
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
     * 目录类型
     */
    private String con_type;
    /**
     * 目录可见（0显示，1隐藏）
     */
    private Integer con_vital;
    /**
     * 目录权限
     */
    private String con_competence;

    /**
     * 目录图标
     */
    private String con_icon;


}
