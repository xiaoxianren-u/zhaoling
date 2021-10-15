package com.edu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author yz
 * @data: 2021/9/21 14:38 星期二
 * @file : GrateFul.java
 */

/**
 * 感谢类
 */

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrateFul {

    /**
     * 感谢id
     */
    public Integer gra_id;
    /**
     * 发表感谢时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date gra_time;
    /**
     * 感谢内容
     */
    public String gra_substance;
    /**
     * 感谢对象
     */
    public Integer gra_bean;

}
