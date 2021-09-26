package com.edu.service;

import com.edu.pojo.ConTents;
import com.edu.pojo.mulu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author yz
 * @data: 2021/9/23 10:51 星期四
 * @file : SysMuluService.java
 */
@Repository
public interface SysMenuService {

    /**
     * 插入新的目录
     *
     * @return
     */
    int insert(mulu m);


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
     * 删除菜单
     *
     * @param conId
     * @return
     */
    HashMap<String, Object> conDeleTe(Integer conId);

    /**
     * 删除目录
     *
     * @param mNumber
     * @return
     */
    int mDeleTe(Integer mNumber);
}
