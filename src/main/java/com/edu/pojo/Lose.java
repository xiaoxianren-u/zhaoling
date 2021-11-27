package com.edu.pojo;

/**
 * @author yz
 * @data: 2021/11/26 22:34 星期五
 * @file : lose.java
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物品丢失情况
 *
 * @author yangzhan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lose {

    private Integer lo_id;

    private Integer value;

    private String name;

}
