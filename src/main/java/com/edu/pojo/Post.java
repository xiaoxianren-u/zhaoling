package com.edu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String user_name;
    /**
     * 帖子标题
     */
    private String post_title;
    /**
     * 帖子状态(-1驳回， 0审核中,1通过，2，待领取，3为已领取)
     */
    private Integer post_status;

    /**
     * 0捡到，1丢失
     */
    private Integer post_status1;
    /**
     * 帖子发表时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
     * 物品图片
     */
    private String post_image;

    /**
     * 帖子拾到人称呼
     */
    private String post_found_call;
    /**
     * 拾到物品时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date post_found_time;
    /**
     * 拾到丢失物品地点
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date post_receive_time;


    public Post(String user_name, String post_title, Integer post_status1, Date post_time, String post_substance, String lab_name, Date post_found_time, String post_found_place) {
        this.user_name = user_name;
        this.post_title = post_title;
        this.post_status1 = post_status1;
        this.post_time = post_time;
        this.post_substance = post_substance;
        this.lab_name = lab_name;
        this.post_found_time = post_found_time;
        this.post_found_place = post_found_place;
    }
}
