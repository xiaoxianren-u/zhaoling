package com.edu.service.impl;

import com.edu.dao.ForLabelDao;
import com.edu.pojo.Label;
import com.edu.service.ForLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/31 18:29 星期日
 * @file : ForLabelServiceImpl.java
 */
@Service
public class ForLabelServiceImpl implements ForLabelService {

    @Autowired
    private ForLabelDao forLabelDao;

    /**
     * 获取没有被禁的帖子标签
     *
     * @return
     */
    @Override
    public List<Label> forList() {
        return forLabelDao.forList();
    }
}
