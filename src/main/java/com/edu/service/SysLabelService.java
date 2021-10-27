package com.edu.service;

import com.edu.pojo.Label;
import com.edu.util.AjaxUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/27 20:04 星期三
 * @file : SysLabelService.java
 */

@Repository
public interface SysLabelService {

    /**
     * 标签列表
     *
     * @return
     */
    List<Label> list();

    /**
     * "删除帖子标签
     *
     * @param lab_id
     * @return
     */
    AjaxUtils del(Integer lab_id);

    /**
     * 修改帖子标签
     *
     * @param label
     * @return
     */
    AjaxUtils up(Label label);

    /**
     * 添加帖子标签
     *
     * @param label
     * @return
     */
    AjaxUtils add(Label label);
}
