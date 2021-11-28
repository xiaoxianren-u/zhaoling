package com.edu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ToString
@ApiModel("用户实体类")
public class User {

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer user_id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String user_name;
    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String pass_word;
    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String user_email;
    /**
     * 用户电话
     */
    @ApiModelProperty("用户电话")
    private String user_iphone;
    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String name;
    /**
     * 用户性别
     */
    @ApiModelProperty("用户性别")
    private String user_sex;
    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private String status;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String user_image;
    /**
     * 用户注册时间
     */
    @ApiModelProperty("用户注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date register_time;
    /**
     * 用户最后一次登录时间
     */
    @ApiModelProperty("用户最后一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finally_time;

    /**
     * 拉黑 (0不拉黑，1拉黑)
     */
    @ApiModelProperty("拉黑")
    private Integer pull_black;

    /**
     * 将时间转换成yy:dd/MM 。。。。。
     */
    @ApiModelProperty("将时间转换")
    private String date;

}
