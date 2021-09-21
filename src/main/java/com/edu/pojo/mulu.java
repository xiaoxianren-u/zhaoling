package com.edu.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 18:35 星期一
 * @file : mulu.java
 */
//父目录
@Data
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
    private String m_icon;
    /**
     * 子目录
     */
    private List<ConTents> list;
}
