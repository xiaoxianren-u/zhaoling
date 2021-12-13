package com.edu.service.impl;

import com.edu.config.UserConfig;
import com.edu.dao.ForLabelDao;
import com.edu.dao.ForPostDao;
import com.edu.dao.ForUserDao;
import com.edu.pojo.Post;
import com.edu.service.ForPostService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private ForLabelDao forLabelDao;

    @Autowired
    private ForUserDao forUserDao;

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
        if (post.getPost_found_time() == null) {
            post.setPost_found_time(new Date());
        }
        if (post.getPost_image() == null) {
            post.setPost_image(forLabelDao.getImage(post.getLab_name()));
        }
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


    /**
     * 我的物品
     *
     * @param status
     * @param request
     * @return
     */

    @Override
    public AjaxUtils selectListApp(Integer status, HttpServletRequest request) {

        try {
            List<Post> list = forPostDao.selectListApp(status, UserConfig.tokenUserName(request));
            return new AjaxUtils(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(false);
        }

    }

    /**
     * 帖子数量
     *
     * @param status
     * @return
     */
    @Override
    public AjaxUtils selectCountIndex(Integer status, String labName, String text, Integer time, Integer postStatus1) {
        return new AjaxUtils(forPostDao.selectCountIndex(status, labName, text, time, postStatus1));
    }

    /**
     * 帖子列表
     *
     * @param status
     * @return
     */

    @Override
    public AjaxUtils selectListIndex(Integer status, int page, int limit, String labName, String text, Integer time, Integer postStatus1) {
        try {
            List<Post> list = forPostDao.selectListIndex(status, page, limit, labName, text, time, postStatus1);
            return new AjaxUtils(AjaxUtils.Type.SUCCESS, true, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(AjaxUtils.Type.ERROR, false);
        }
    }


    /**
     * 帖子内容
     *
     * @param t
     * @return
     */
    @Override
    public Post selectPo(Integer t) {
        return forPostDao.selectPo(t);
    }

    /**
     * 申请归还
     *
     * @param tokenUserName
     * @param post_id
     * @return
     */

    @Override
    public AjaxUtils getPost(String tokenUserName, Integer post_id) {

        if (forPostDao.selectPo(post_id).getUser_name().equals(tokenUserName)) {
            return new AjaxUtils(false, "不得申请自己发的帖子");
        }
        try {
            forPostDao.getPost(tokenUserName, forUserDao.selectName(tokenUserName).getUser_iphone(), post_id, 1);
            return new AjaxUtils(true, "申请成功，请前往个人中心查看!");
        } catch (Exception e) {
            return new AjaxUtils(false, "申请失败!");
        }
    }


    /**
     * 拒绝归还
     *
     * @param post_id
     * @return
     */
    @Override
    public AjaxUtils getExit(Integer post_id) {

        try {
            forPostDao.getExit(post_id);
            return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } catch (Exception e) {
            return new AjaxUtils("操作失败！");
        }
    }

    /**
     * 归还物品
     *
     * @param time
     * @param post_image
     * @return
     */
    @Override
    public AjaxUtils setWu(String time, String post_image, Integer post_id) {
        Date postReceiveTime = null;
        if ("".equals(time)) {
            postReceiveTime = new Date();
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                postReceiveTime = formatter.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if ("".equals(post_image)) {
            return new AjaxUtils(false, "图片不能为空");
        }
        try {
            forPostDao.setWu(postReceiveTime, post_image, post_id);
            return new AjaxUtils(true, "归还成功");
        } catch (Exception e) {
            return new AjaxUtils(false, "归还失败");
        }


    }
}
