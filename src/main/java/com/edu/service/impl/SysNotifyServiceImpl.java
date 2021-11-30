package com.edu.service.impl;

import com.edu.dao.SysNotifyDao;
import com.edu.pojo.NotiFy;
import com.edu.service.SysNotifyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/24 15:43 星期日
 * @file : SysNotifyServiceImpl.java
 */
@Service
public class SysNotifyServiceImpl implements SysNotifyService {


    @Autowired
    private SysNotifyDao sysNotifyDao;


    /**
     * 通知列表
     *
     * @param notiStatus
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<NotiFy> list(Integer notiStatus, Integer page, Integer limit) {
        return sysNotifyDao.list(notiStatus, page, limit);
    }

    /**
     * 通知数量
     *
     * @param notiStatus
     * @return
     */
    @Override
    public int count(Integer notiStatus) {
        return sysNotifyDao.count(notiStatus);
    }

    /**
     * 删除通知
     *
     * @param noti_id
     */
    @Override
    public void del(Integer noti_id) {
        sysNotifyDao.del(noti_id);
    }

    /**
     * 添加通知公告
     *
     * @param notiFy
     */
    @Override
    public void insertT(@NotNull NotiFy notiFy) {

        sysNotifyDao.insertT(notiFy);
    }

    /**
     * 用户通知
     *
     * @return
     */

    @Override
    public List<NotiFy> userList() {
        return sysNotifyDao.userList();
    }
}
