package com.edu.dao;

import com.edu.pojo.NotiFy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/24 15:44 星期日
 * @file : SysNotifyDao.java
 */
@Component
public interface SysNotifyDao {


    /**
     * 通知列表
     *
     * @param notiStatus
     * @param page
     * @param limit
     * @return
     */

    List<NotiFy> list(@Param("notiStatus") Integer notiStatus, @Param("page") Integer page, @Param("limit") Integer limit);


    /**
     * 总的通知数量
     *
     * @param notiStatus
     * @return
     */
    int count(@Param("notiStatus") Integer notiStatus);

    /**
     * 删除通知
     *
     * @param noti_id
     */
    void del(@Param("noti_id") Integer noti_id);


    /**
     * @param notiFy
     */
    int insertT(NotiFy notiFy);

}
