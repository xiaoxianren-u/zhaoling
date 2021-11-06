package com.edu.service.impl;

import com.edu.config.UserConfig;
import com.edu.dao.ForPostDao;
import com.edu.pojo.Post;
import com.edu.service.ForPostService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/11/6 14:13 星期六
 * @file : ForPostServiceImpl.java
 */

@Service
public class ForPostServiceImpl implements ForPostService {


    @Autowired
    private ForPostDao forPostDao;

    /**
     * 新增帖子
     *
     * @param post
     * @return
     */

    @Override
    public AjaxUtils insertPost(@NotNull Post post, HttpServletRequest request) {

        post.setUser_name(UserConfig.tokenUserName(request));
        post.setPost_time(new Date());
        try {
            forPostDao.insertPost(post);
            return new AjaxUtils(PageCodeEnum.ADD_SUCCESS.getBool(), PageCodeEnum.ADD_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.ADD_FAIL.getBool(), PageCodeEnum.ADD_FAIL.getMsg());
        }
    }


    /**
     * 个人帖子表
     *
     * @param status
     * @param request
     * @return
     */
    @Override
    public AjaxUtils selectList(Integer status, HttpServletRequest request) {

        try {
            List<Post> list = forPostDao.selectList(status, UserConfig.tokenUserName(request));
            return new AjaxUtils(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(false);
        }

    }
}
