package com.edu.controller.back;

import com.edu.config.UserConfig;
import com.edu.intercept.PassToken;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.GrateFul;
import com.edu.service.GrateFulService;
import com.edu.util.AjaxUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @PassToken
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


    /**
     * 添加感谢信
     */
    @UserLoginToken(state = 0)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxUtils insert(@NotNull @RequestBody GrateFul grateFul, HttpServletRequest request) {
        grateFul.setUsername(UserConfig.tokenUserName(request));
        AjaxUtils ajaxUtils = grateFulService.insert(grateFul);
        System.out.println("ajaxUtils = " + ajaxUtils);
        return ajaxUtils;
    }

    /**
     * 审核通过
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/up")
    @UserLoginToken(state = 1)
    public AjaxUtils update(@NotNull @RequestParam("id") Integer id) {
        System.out.println("id = " + id);
        return grateFulService.update(id);
    }

    @PassToken
    @RequestMapping(value = "/ganXieList")
    public AjaxUtils ganXieList() {
        return grateFulService.ganXieList();
    }


}
