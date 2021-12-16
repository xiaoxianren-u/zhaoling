package com.edu.controller.back;

import com.edu.intercept.UserLoginToken;
import com.edu.service.GrateFulService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yz
 * @data: 2021/12/15 20:50 星期三
 * @file : GrateFulController.java
 */

@RestController
@RequestMapping(value = "/gre")
public class GrateFulController {


    @Autowired
    private GrateFulService grateFulService;

    /**
     * 感谢信表
     *
     * @param page
     * @param limit
     * @param username
     * @param date
     * @return
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/list")
    public AjaxUtils list(@RequestParam("page") Integer page,
                          @RequestParam("limit") Integer limit,
                          @RequestParam("username") String username,
                          @RequestParam("date") String date) {
        return grateFulService.list(page, limit, username, date);
    }

    /**
     * 删除感谢信
     *
     * @param id
     * @return
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/del")
    public AjaxUtils del(@RequestParam("id") Integer id) {
        return grateFulService.del(id);
    }

}
