package com.edu.service;

import com.edu.pojo.ConTents;
import com.edu.pojo.mulu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yz
 * @data: 2021/9/23 10:51 星期四
 * @file : SysMuluService.java
 */
@Repository
public interface SysMuluService {

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


}
