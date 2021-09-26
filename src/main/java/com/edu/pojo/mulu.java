package com.edu.pojo;

import lombok.*;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 18:35 星期一
 * @file : mulu.java
 */
//父目录
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class mulu {

    /**
     * 父目录id
     */

    private Integer m_id;
    /**
     * 父目录名称
     */
    private String m_name;
    /**
     * 父目录编号
     */
    private Integer m_number;
    /**
     * 父目录图标
     */
    private String m_url;
    /**
     * 父目录类型
     */
    private String m_type;
    /**
     * 父目录可见（0显示，1隐藏）
     */
    private Integer m_vital;
    /**
     * 父目录权限
     */
    private String m_competence;
    /**
     * 父目录图标
     */
    private String m_icon;
    /**
     * 子目录
     */
    private List<ConTents> list;
}
