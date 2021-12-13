package com.edu.service.impl;

import com.edu.dao.FeedbackDao;
import com.edu.service.FeedbackService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
