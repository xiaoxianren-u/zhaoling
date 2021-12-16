package com.edu.service.impl;

import com.edu.dao.GrateFulDao;
import com.edu.pojo.GrateFul;
import com.edu.service.GrateFulService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author yz
 * @data: 2021/12/15 20:51 星期三
 * @file : GrateFulServiceImpl.java
 */

@Service
public class GrateFulServiceImpl implements GrateFulService {


    @Autowired
    private GrateFulDao grateFulDao;

    /**
     * 感谢信表
     *
     * @param page
     * @param limit
     * @param username
     * @param date
     * @return
     */
    @Override
    public AjaxUtils list(Integer page, Integer limit, String username, String date) {
        GrateFul g = new GrateFul(null, null, null, username, null);
        if (date != null && !"".equals(date)) {
            try {
                g.setGra_time(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        page = page > 1 ? limit * (page - 1) : 0;
        try {
            int n = grateFulDao.count(g);
            List<GrateFul> list = grateFulDao.list(page, limit, g);
            return new AjaxUtils(n, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除感谢信
     *
     * @param id
     * @return
     */
    @Override
    public AjaxUtils del(Integer id) {

        try {
            grateFulDao.del(id);
            return new AjaxUtils(PageCodeEnum.REMOVE_SUCCESS.getBool(), PageCodeEnum.REMOVE_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg());
    }
}
