package com.edu.service;

import com.edu.pojo.Post;
import com.edu.util.AjaxUtils;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yz
 * @data: 2021/11/6 14:13 星期六
 * @file : ForPostService.java
 */
@Repository
public interface ForPostService {


    /**
     * 新增帖子
     *
     * @param post
     * @return
     */
    AjaxUtils insertPost(Post post, HttpServletRequest request);

    /**
     * 个人帖子表
     *
     * @param status
     * @param request
     * @return
     */
    AjaxUtils selectList(Integer status, HttpServletRequest request);

    /**
     * 首页帖子数量
     *
     * @param status
     * @return
     */
    AjaxUtils selectCountIndex(Integer status, String labName, String text, Integer time, Integer postStatus1);

    /**
     * 首页内容列表
     *
     * @param status
     * @return
     */
    AjaxUtils selectListIndex(Integer status, int page, int limit, String labName, String text, Integer time, Integer postStatus1);


}
