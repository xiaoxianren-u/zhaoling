package com.edu.dao;

import com.edu.pojo.GrateFul;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/12/15 20:51 星期三
 * @file : GrateFulDao.java
 */
@Repository
public interface GrateFulDao {


    /**
     * 感谢信数量
     *
     * @param g
     * @return
     */
    int count(GrateFul g);


    /**
     * 感谢信内容
     *
     * @param g
     * @return
     */
    List<GrateFul> list(@Param("page") Integer page, @Param("limit") Integer limit, @Param("g") GrateFul g);

    /**
     * 删除感谢信
     *
     * @param id
     */
    void del(Integer id);

    /**
     * 新增
     *
     * @param grateFul
     */
    void insert(GrateFul grateFul);

    /**
     * 审核通过
     *
     * @param id
     * @return
     */
    void update(Integer id);

    /**
     * 感谢页面的内容
     *
     * @return
     */
    List<GrateFul> ganXieList();
}
