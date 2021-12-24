package com.edu.controller.front;

import com.edu.config.UserConfig;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.GrateFul;
import com.edu.service.GrateFulService;
import com.edu.util.AjaxUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yz
 * @data: 2021/12/24 20:32 星期五
 * @file : ForGrateFulController.java
 */
@RestController
@RequestMapping("/gt")
public class ForGrateFulController {


    @Autowired
    private GrateFulService grateFulService;


    /**
     * 添加感谢信
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxUtils insert(@NotNull @RequestBody GrateFul grateFul, HttpServletRequest request) {
        grateFul.setUsername(UserConfig.tokenUserName(request));
        AjaxUtils ajaxUtils = grateFulService.insert(grateFul);
        System.out.println("ajaxUtils = " + ajaxUtils);
        return ajaxUtils;
    }
}
