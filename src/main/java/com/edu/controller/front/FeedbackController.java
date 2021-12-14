package com.edu.controller.front;

import com.edu.intercept.UserLoginToken;
import com.edu.service.FeedbackService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yz
 * @data: 2021/12/13 22:18 星期一
 * @file : FeedbackController.java
 */

/**
 * 意见反馈
 *
 * @author yangzhan
 */

@RestController
@RequestMapping(value = "/feed")
public class FeedbackController {


    @Autowired
    private FeedbackService feedbackService;

    /**
     * 添加意见反馈
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public AjaxUtils feedBackAdd(@RequestParam("text") String text) {
        return feedbackService.feedBackAdd(text);
    }


    /**
     * 意见反馈列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list")
    @UserLoginToken(state = 1)
    public AjaxUtils sel(@RequestParam("page") Integer page,
                         @RequestParam("limit") Integer limit) {
        return feedbackService.sel(page, limit);
    }

    /**
     * 删除意见反馈
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del")
    public AjaxUtils del(@RequestParam("id") Integer id) {
        return feedbackService.del(id);
    }

}
