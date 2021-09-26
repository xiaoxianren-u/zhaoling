package com.edu.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author yz
 * @data: 2021/9/21 14:03 星期二
 * @file : Post.java
 */

/**
 * 帖子表
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    /**
     * 帖子id （主键）
     */
    private Integer post_id;
    /**
     * 用户id  （外键）
     */
    private Integer user_id;
    /**
     * 帖子标题
     */
    private String post_title;
    /**
     * 帖子状态(0审核中,1通过，2，已领取，3为审核同)
     */
    private Integer post_status;

    /**
     * 帖子发表时间
     */
    private Date post_time;
    /**
     * 帖子内容
     */
    private String post_substance;

    /**
     * 帖子标签  （外键）
     */
    private String lab_name;
    /**
     * 帖子拾到人联系方式(手机、qq、微信、邮箱)任何一种
     */
    private String post_found_link;
    /**
     * 帖子拾到人称呼
     */
    private String post_found_call;
    /**
     * 拾到物品时间
     */
    private Date post_found_time;
    /**
     * 拾到物品地点
     */
    private String post_found_place;
    /**
     * 领取人联系方式(手机、qq、微信、邮箱)任何一种
     */
    private String post_receive_link;
    /**
     * 领取人名字
     */
    private String post_receive_name;
    /**
     * 领取时间
     */
    private Date post_receive_time;

}
