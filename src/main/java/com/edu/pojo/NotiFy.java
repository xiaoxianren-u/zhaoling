package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/9/21 14:46 星期二
 * @file : NotiFy.java
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 通知公告
 *
 * @author yangzhan
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
     * 通知标题
     */
    private String noti_name;


    /**
     * 发通知的用户
     */
    private String user_name;
    /**
     * 通知内容
     */
    private String noti_substance;
    /**
     * 通知时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date noti_time;
    /**
     * 通知状态（0，普通，1重要，2紧急，3过时）
     */
    private Integer noti_status;


    @ApiModelProperty("将时间转换")
    private String date;
}
