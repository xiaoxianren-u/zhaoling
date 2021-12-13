package com.edu.service.impl;

import com.edu.dao.SysLabelDao;
import com.edu.pojo.Label;
import com.edu.service.SysLabelService;
import com.edu.service.TimedService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz
 * @data: 2021/10/27 20:05 星期三
 * @file : SysLabelServiceImpl.java
 */

@Service
public class SysLabelServiceImpl implements SysLabelService {


    @Autowired
    private SysLabelDao sysLabelDao;

    @Autowired
    private TimedService timedService;

    /**
     * 标签列表
     *
     * @return
     */
    @Override
    public List<Label> list() {
        return sysLabelDao.list();
    }

    /**
     * "删除帖子标签
     *
     * @param lab_id
     * @return
     */
    @Override
    public AjaxUtils del(Integer lab_id) {

        try {
            timedService.dele(sysLabelDao.sel(lab_id));
            sysLabelDao.del(lab_id);

            return new AjaxUtils(PageCodeEnum.REMOVE_SUCCESS.getBool(), PageCodeEnum.REMOVE_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg() + ",出现异常");
        }
    }


    /**
     * 修改帖子标签
     *
     * @param label
     * @return
     */
    @Override
    public AjaxUtils up(Label label) {
        try {
            sysLabelDao.up(label);
            timedService.update(label.getLab_name(), sysLabelDao.sel(label.getLab_id()));
            return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), PageCodeEnum.MODIFY_FAIL.getMsg() + ",出现异常");
        }
    }


    /**
     * 添加帖子标签
     *
     * @param label
     * @return
     */
    @Override
    public AjaxUtils add(Label label) {
        try {
            sysLabelDao.add(label);
            timedService.add(label.getLab_name());
            return new AjaxUtils(PageCodeEnum.ADD_SUCCESS.getBool(), PageCodeEnum.ADD_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.ADD_FAIL.getBool(), PageCodeEnum.ADD_FAIL.getMsg() + ",出现异常");
        }
    }
}
