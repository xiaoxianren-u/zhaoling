package com.edu.dao;

import com.edu.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
     * 个人帖子列表
     *
     * @param status
     * @param tokenUserName
     * @return
     */
    List<Post> selectList(@Param("status") Integer status, @Param("tokenUserName") String tokenUserName);


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
}
