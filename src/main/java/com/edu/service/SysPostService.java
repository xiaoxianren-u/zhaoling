package com.edu.service;

import com.edu.pojo.Post;
import com.edu.util.AjaxUtils;
import org.springframework.stereotype.Repository;

/**
 * @author yz
 * @data: 2021/11/15 21:27 星期一
 * @file : SysPostService.java
 */

@Repository
public interface SysPostService {


    /**
     * 帖子列表
     *
     * @return
     */
    AjaxUtils selectAll(Integer page, Integer limit, Post post);

    /**
     * 修改物品状态
     *
     * @param postId
     * @param postStatus
     * @return
     */
    AjaxUtils upPost(Integer postId, Integer postStatus);

    /**
     * 删除物品
     *
     * @param postId
     * @return
     */
    AjaxUtils delPost(Integer postId);
}
