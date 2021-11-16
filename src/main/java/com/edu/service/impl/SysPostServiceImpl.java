package com.edu.service.impl;

import com.edu.dao.SysPostDao;
import com.edu.pojo.Post;
import com.edu.service.SysPostService;
import com.edu.util.AjaxUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yz
 * @data: 2021/11/15 21:27 星期一
 * @file : SysPostServiceImpl.java
 */

@Service
public class SysPostServiceImpl implements SysPostService {


    @Autowired
    private SysPostDao sysPostDao;


    /**
     * 帖子列表
     *
     * @param page
     * @param limit
     * @param post
     * @return
     */
    @Override
    public AjaxUtils selectAll(Integer page, Integer limit, @NotNull Post post) {

        // 中文乱码
        if (!"".equals(post.getPost_title()) && post.getPost_title() != null) {
            post.setPost_title(new String(post.getPost_title().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        } else {
            post.setPost_title(null);
        }
        if (!"".equals(post.getLab_name()) && post.getLab_name() != null) {
            post.setLab_name(new String(post.getLab_name().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        } else {
            post.setLab_name(null);
        }
        if (!"".equals(post.getPost_found_place()) && post.getPost_found_place() != null) {
            post.setPost_found_place(new String(post.getPost_found_place().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        } else {
            post.setPost_found_place(null);
        }
        if (!"".equals(post.getPost_substance()) && post.getPost_substance() != null) {
            post.setPost_substance(new String(post.getPost_substance().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        } else {
            post.setPost_substance(null);
        }
        page = page > 1 ? limit * (page - 1) : 0;


        try {
            int n = sysPostDao.selectCount(post);
            List<Post> list = sysPostDao.selectAll(page, limit, post);
            return new AjaxUtils(0, "", n, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
