package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/9/21 14:46 星期二
 * @file : NotiFy.java
 */

import lombok.*;

import java.util.Date;

/**
 * 通知公告
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotiFy {

    /**
     * 通知id
     */
    private Integer noti_id;
    /**
     * 通知内容
     */
    private String noti_substance;
    /**
     * 通知时间
     */
    private Date noti_time;
    /**
     * 通知状态（0，普通，1重要，2紧急，3过时）
     */
    private Integer noti_status;
}
