package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/10/29 20:37 星期五
 * @file : Feedback.java
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 意见反馈
 *
 * @author yangzhan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    private Integer fe_id;

    /**
     * 反馈内容
     */
    private String fe_content;

    /**
     * 0为待处理，1已处理
     */
    private Integer status;

    /**
     * 反馈时间
     */

    private Date fe_time;
    /**
     * 时间转换
     */
    private String date;

}
