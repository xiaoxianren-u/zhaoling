package com.edu.service.impl;

import com.edu.dao.SysConTentsDao;
import com.edu.pojo.ConTents;
import com.edu.service.SysConTentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 17:41 星期一
 * @file : SysConTentServiceImpl.java
 */

@Service
public class SysConTentServiceImpl implements SysConTentService {

    @Autowired
    public SysConTentsDao sysConTentsDao;

    @Override
    public List<ConTents> selectContents() {
        return sysConTentsDao.selectContents();
    }
}
