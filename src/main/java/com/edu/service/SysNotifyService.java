package com.edu.service;

import com.edu.pojo.NotiFy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/24 15:39 星期日
 * @file : SysNotifyService.java
 */

@Repository
public interface SysNotifyService {


    /**
     * 通知列表
     *
     * @param notiStatus
     * @return
     */
    List<NotiFy> list(Integer notiStatus, Integer page, Integer limit);

    /**
     * 同时数量
     *
     * @param notiStatus
     * @return
     */
    int count(Integer notiStatus);

    /**
     * 删除通知
     *
     * @param noti_id
     */
    void del(Integer noti_id);

    /**
     * 添加通知公告
     *
     * @param notiFy
     */
    int insertT(NotiFy notiFy);
}
