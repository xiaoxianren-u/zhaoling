package com.edu.service;

import com.edu.util.AjaxUtils;
import org.springframework.stereotype.Component;

/**
 * @author yz
 * @data: 2021/12/13 22:19 星期一
 * @file : FeedbackService.java
 */

/**
 * 意见反馈
 *
 * @author yangzhan
 */
@Component
public interface FeedbackService {

    /**
     * 意见反馈
     *
     * @param text
     * @return
     */
    AjaxUtils feedBackAdd(String text);
}
