package com.edu.service.impl;

import com.edu.dao.SysMenuDao;
import com.edu.pojo.ConTents;
import com.edu.pojo.mulu;
import com.edu.service.SysMenuService;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author yz
 * @data: 2021/9/23 10:51 星期四
 * @file : SysMuluServiceImpl.java
 */

@Service
public class SysMenuServiceImpl implements SysMenuService {


    /**
     * 父目录数据库接口
     */
    @Autowired
    private SysMenuDao sysMenuDao;


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
        int n = sysMenuDao.selectM_number();
        m.setM_number(n + 1);
        /**
         * 目录添加
         */
        int nn = sysMenuDao.insert(m);
        if (nn > 0) {
            /**
             * 第一个菜单添加
             */
            return sysMenuDao.insetM_Con(m);
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
        return sysMenuDao.inset_Con(conTents);
    }

    /**
     * 获取要修改指定目录的字段
     *
     * @param mNumber
     * @return
     */
    @Override
    public mulu selectMone(Integer mNumber) {
        return sysMenuDao.selectMone(mNumber);
    }

    /**
     * 目录修改
     *
     * @param m
     * @return
     */
    @Override
    public int updateM_nu(mulu m) {
        return sysMenuDao.updateM_nu(m);
    }


    /**
     * 获取要修改指定菜单的字段
     *
     * @param conNumber
     * @return
     */
    @Override
    public ConTents selectConone(Integer conNumber) {
        return sysMenuDao.selectConone(conNumber);
    }

    /**
     * 修改菜单
     *
     * @param conTents
     * @return
     */
    @Override
    public int updateCon_nu(ConTents conTents) {
        return sysMenuDao.updateCon_nu(conTents);
    }


    /**
     * 删除菜单
     *
     * @param conId
     * @return
     */
    @Override
    public HashMap<String, Object> conDeleTe(Integer conId) {

        HashMap<String, Object> map = new HashMap<>(2);
        /**
         * 查询改菜单的父目录编号
         */

        int number = sysMenuDao.selectNumber(conId);
        /**
         * 查询该编号有多少条，如果有一条不允许删除，两条及两条以上可以删除
         */
        int count = sysMenuDao.conCount(number);
        if (count > 1) {
            int n = sysMenuDao.conDeleTe(conId);
            if (n > 0) {
                map.put("msg", PageCodeEnum.REMOVE_SUCCESS.getMsg());
                map.put("bool", PageCodeEnum.REMOVE_SUCCESS.getBool());
            } else {
                map.put("msg", PageCodeEnum.REMOVE_FAIL.getMsg());
                map.put("bool", PageCodeEnum.REMOVE_FAIL.getBool());
            }
        } else {
            map.put("msg", "该菜单不可以直接删除，若要删除请删除上级目录");
            map.put("bool", false);
        }
        return map;
    }

    /**
     * 删除目录
     *
     * @param mNumber
     * @return
     */

    @Override
    public int mDeleTe(Integer mNumber) {
        /**
         * 对目录的子菜单先进行删除
         */
        int m = sysMenuDao.conDelete_con(mNumber);
        if (m > 0) {
            return sysMenuDao.mDeleTe(mNumber);
        }
        return m;
    }
}
