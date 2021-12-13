package com.edu.dao;

import com.edu.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/11/6 14:12 星期六
 * @file : ForPostDao.java
 */

@Component
public interface ForPostDao {
    /**
     * @param post
     * @return
     */
    void insertPost(Post post);

    /**
     * 发布帖子列表
     *
     * @param status
     * @param tokenUserName
     * @return
     */
    List<Post> selectList(@Param("status") Integer status, @Param("tokenUserName") String tokenUserName);


    /**
     * 我的物品
     *
     * @param status
     * @param tokenUserName
     * @return
     */

    List<Post> selectListApp(@Param("status") Integer status, @Param("tokenUserName") String tokenUserName);


    /**
     * 帖子数量
     *
     * @param status
     * @return
     */
    Integer selectCountIndex(@Param("status") Integer status, @Param("labName") String labName,
                             @Param("text") String text, @Param("time") Integer time,
                             @Param("postStatus1") Integer postStatus1);

    /**
     * 首页内容列表
     *
     * @param status
     * @return
     */
    List<Post> selectListIndex(@Param("status") Integer status, @Param("page") Integer page,
                               @Param("limit") Integer limit, @Param("labName") String labName,
                               @Param("text") String text, @Param("time") Integer time,
                               @Param("postStatus1") Integer postStatus1);


    /**
     * 帖子内容
     *
     * @param t
     * @return
     */
    Post selectPo(@Param("t") Integer t);

    /**
     * 申请归还
     *
     * @param tokenUserName
     * @param post_id
     */
    void getPost(@Param("tokenUserName") String tokenUserName, @Param("home") String home,
                 @Param("post_id") Integer post_id, @Param("status") Integer status);

    /**
     * 拒绝归还
     *
     * @param post_id
     */
    void getExit(@Param("post_id") Integer post_id);

    /**
     * 物品归还
     *
     * @param postReceiveTime
     * @param post_image
     */
    void setWu(@Param("postReceiveTime") Date postReceiveTime, @Param("post_image") String post_image, @Param("post_id") Integer post_id);
}
