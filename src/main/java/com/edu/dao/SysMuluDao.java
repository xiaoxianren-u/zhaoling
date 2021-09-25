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
public interface SysMuluDao {


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


}
