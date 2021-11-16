package com.edu.service.impl;

import com.edu.dao.SysPostDao;
import com.edu.pojo.Post;
import com.edu.service.SysPostService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public AjaxUtils selectAll(Integer page, Integer limit, Post post) {


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
