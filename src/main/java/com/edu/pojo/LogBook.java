package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/10/20 14:29 星期三
 * @file : LogBook.java
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 系统日志
 *
 * @author yangzhan
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogBook {

    /**
     * 日志id
     */
    private Integer log_id;
    /**
     * 开始时间
     */
    private Date log_date;
    /**
     * 浏览器
     */
    private String log_browser;
    /**
     * 执行的类与方法
     */
    private String log_class_method;
    /**
     * 请求类型
     */
    private String log_url_type;

    /**
     * 请求url
     */
    private String log_url;

    /**
     * ip地址
     */
    private String ip;
    /**
     * 登录的用户
     */
    private String log_user;

    /**
     * 方法执行时间
     */
    private Double log_time;
    /**
     * 登录成功还是失败
     */
    private String log_start;


    /**
     * 用来识别是登录日志还是操作日志（0，登录日志，1 操作日志）
     */
    private Integer discern;

    /**
     * 将时间转换成yy:dd/MM 。。。。。
     */
    @ApiModelProperty("将时间转换")
    private String date;


}
