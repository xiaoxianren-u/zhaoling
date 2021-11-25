package com.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yz
 * @data: 2021/11/23 13:16 星期二
 * @file : UserMessage.java
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {


    /**
     * 消息发送者
     */
    private String username;

    /**
     * 聊天文本
     */
    private String message;

    /**
     * 消息接受者
     */
    private String tousername;

    /**
     * 发送时间
     */
    private Date createtime;


}
