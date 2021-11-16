package com.edu.dao;

import com.edu.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/11/15 21:27 星期一
 * @file : SysPostDao.java
 */

@Component
public interface SysPostDao {


    /**
     * 帖子数量
     */
    int selectCount(Post post);

    /**
     * 帖子列表
     *
     * @return
     */
    List<Post> selectAll(@Param("page") Integer page, @Param("limit") Integer limit, @Param("post") Post post);


}
