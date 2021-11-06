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

    List<Post> selectList(@Param("status") Integer status, @Param("tokenUserName") String tokenUserName);
}
