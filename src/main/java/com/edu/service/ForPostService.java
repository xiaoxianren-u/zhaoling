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
     * 发布帖子表
     *
     * @param status
     * @param request
     * @return
     */
    AjaxUtils selectList(Integer status, HttpServletRequest request);


    /**
     * 我的物品表
     *
     * @param status
     * @param request
     * @return
     */
    AjaxUtils selectListApp(Integer status, HttpServletRequest request);

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


    /**
     * 帖子内容
     *
     * @param t
     * @return
     */
    Post selectPo(Integer t);

    /**
     * 申请归还至
     *
     * @param tokenUserName
     * @param post_id
     * @return
     */
    AjaxUtils getPost(String tokenUserName, Integer post_id);

    /**
     * 拒绝归还
     *
     * @param post_id
     * @return
     */
    AjaxUtils getExit(Integer post_id);


    /**
     * 归还物品
     *
     * @param time
     * @param post_image
     * @return
     */
    AjaxUtils setWu(String time, String post_image, Integer post_id);
}
