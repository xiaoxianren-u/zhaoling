package com.edu.service.impl;

import com.edu.dao.SysMuluDao;
import com.edu.pojo.ConTents;
import com.edu.pojo.mulu;
import com.edu.service.SysMuluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yz
 * @data: 2021/9/23 10:51 星期四
 * @file : SysMuluServiceImpl.java
 */
@Service
public class SysMuluServiceImpl implements SysMuluService {


    /**
     * 父目录数据库接口
     */
    @Autowired
    private SysMuluDao sysMuluDao;


    /**
     * 目录插入
     *
     * @param m
     * @return
     */
    @Override
    public int insert(mulu m) {
        /**
         * 先获取目录最大编号在 +1
         */
        int n = sysMuluDao.selectM_number();
        m.setM_number(n + 1);
        /**
         * 目录添加
         */
        int nn = sysMuluDao.insert(m);
        if (nn > 0) {
            /**
             * 第一个菜单添加
             */
            return sysMuluDao.insetM_Con(m);
        } else {
            return nn;
        }
    }

    /**
     * 插入新的菜单
     *
     * @param conTents
     * @return
     */
    @Override
    public int inset_Con(ConTents conTents) {
        return sysMuluDao.inset_Con(conTents);
    }

    /**
     * 获取要修改指定目录的字段
     *
     * @param mNumber
     * @return
     */
    @Override
    public mulu selectMone(Integer mNumber) {
        return sysMuluDao.selectMone(mNumber);
    }
}
