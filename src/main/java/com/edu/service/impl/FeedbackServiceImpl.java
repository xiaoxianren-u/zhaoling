package com.edu.service.impl;

import com.edu.dao.FeedbackDao;
import com.edu.pojo.Feedback;
import com.edu.service.FeedbackService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/12/13 22:20 星期一
 * @file : FeedbackServiceImpl.java
 */

/**
 * 意见反馈
 *
 * @author yangzhan
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {


    @Autowired
    private FeedbackDao feedbackDao;

    /**
     * 添加意见反馈
     *
     * @param text
     * @return
     */
    @Override
    public AjaxUtils feedBackAdd(String text) {

        System.out.println("text = " + text);
        if ("".equals(text)) {
            return new AjaxUtils(false, "反馈内容不能为空!");
        }
        try {
            feedbackDao.feedBackAdd(text, new Date());
            return new AjaxUtils(PageCodeEnum.ADD_SUCCESS.getBool(), PageCodeEnum.ADD_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.ADD_FAIL.getBool(), PageCodeEnum.ADD_FAIL.getMsg());
        }
    }


    /**
     * 意见反馈表
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public AjaxUtils sel(Integer page, Integer limit) {
        page = page > 1 ? limit * (page - 1) : 0;
        List<Feedback> list = feedbackDao.sel(page, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Feedback f : list) {
            f.setDate(formatter.format(f.getFe_time()));
        }

        try {
            return new AjaxUtils(feedbackDao.count(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除意见反馈
     *
     * @param id
     * @return
     */
    @Override
    public AjaxUtils del(Integer id) {
        try {
            feedbackDao.del(id);
            return new AjaxUtils(PageCodeEnum.REMOVE_SUCCESS.getBool(), PageCodeEnum.REMOVE_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg());
    }
}
