package com.edu.service;

import com.edu.pojo.Label;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/31 18:28 星期日
 * @file : ForLabelService.java
 */
@Repository
public interface ForLabelService {

    /**
     * 获取没有被禁的帖子标签
     *
     * @return
     */
    List<Label> forList();
}
