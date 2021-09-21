package com.edu.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author yz
 * @data: 2021/9/21 13:32 星期二
 * @file : User.java
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 用户id
     */
    private Integer user_id;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 用户密码
     */
    private String pass_word;
    /**
     * 用户邮箱
     */
    private String user_email;
    /**
     * 用户电话
     */
    private String user_iphone;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户性别
     */
    private String user_sex;
    /**
     * 用户状态（0 普通，1管理员，2超级管理员，3黑名单）
     */
    private Integer status;
    /**
     * 用户头像
     */
    private String user_image;
    /**
     * 用户注册时间
     */
    private Date register_time;
    /**
     * 用户最后一次登录时间
     */
    private Date finally_time;


}
