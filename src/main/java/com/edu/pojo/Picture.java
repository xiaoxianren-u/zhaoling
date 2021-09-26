package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/9/21 14:53 星期二
 * @file : Picture.java
 */

import lombok.*;

/**
 * 图片集
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Picture {

    /**
     * 图片id
     */
    public Integer pic_id;
    /**
     * 图片链接
     */
    public String pic_image;
    /**
     * 图片对应的帖子号
     */
    public Integer post_id;

}
