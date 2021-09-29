package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/9/29 14:08 星期三
 * @file : Role.java
 */

import lombok.*;

/**
 * 角色
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * 角色id
     */
    private Integer ro_id;
    /**
     * 角色类型
     */
    private String status;

    /**
     * 角色状态 0,启用，1禁止
     */
    private Integer ro_status;

    /**
     * 拥有权限
     */
    private String ro_have;
    /**
     * 具体描述
     */
    private String ro_describe;
}
