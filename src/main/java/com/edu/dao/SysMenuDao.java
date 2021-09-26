package com.edu.dao;

import com.edu.pojo.ConTents;
import com.edu.pojo.mulu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author yz
 * @data: 2021/9/23 10:45 星期四
 * @file : SysMuluDao.java
 */

@Component
public interface SysMenuDao {


    /**
     * 获取目录编号做大
     *
     * @return int
     */
    Integer selectM_number();

    /**
     * 插入新的目录
     *
     * @return
     */
    int insert(mulu m);

    /**
     * 添加目录时同时添加该目录的第一个菜单
     */
    int insetM_Con(mulu m);


    /**
     * 插入新的菜单
     *
     * @param conTents
     * @return
     */
    int inset_Con(ConTents conTents);

    /**
     * 获取要修改指定目录的字段
     *
     * @param mNumber
     * @return
     */
    mulu selectMone(@Param("mNumber") Integer mNumber);


    /**
     * 修改目录
     *
     * @param m
     * @return
     */
    int updateM_nu(mulu m);

    /**
     * 获取要修改指定菜单的字段
     *
     * @param conNumber
     * @return
     */
    ConTents selectConone(@Param("conNumber") Integer conNumber);

    /**
     * 修改菜单
     *
     * @param conTents
     * @return
     */
    int updateCon_nu(ConTents conTents);


    /**
     * 查询改菜单的父目录编号
     *
     * @param conId
     * @return
     */

    int selectNumber(Integer conId);

    /**
     * 查询该编号有多少条，如果有一条不允许删除，两条及两条以上可以删除
     *
     * @param number
     * @return
     */
    int conCount(int number);

    /**
     * 删除菜单
     *
     * @param conId
     * @return
     */
    int conDeleTe(Integer conId);

    /**
     * 删除目录
     *
     * @param mNumber
     * @return
     */
    int mDeleTe(Integer mNumber);

    /**
     * 对目录的子菜单先进行删除
     *
     * @param mNumber
     * @return
     */
    int conDelete_con(Integer mNumber);


}
