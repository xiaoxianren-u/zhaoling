package com.edu.controller.front;

import com.edu.config.UserConfig;
import com.edu.service.ForUserService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yz
 * @data: 2021/10/31 19:07 星期日
 * @file : ForUserController.java
 */
@RestController
@RequestMapping(value = "/for/user")
public class ForUserController extends AjaxUtils {


    @Autowired
    private ForUserService forUserService;


    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public AjaxUtils getName(HttpServletRequest request) {

        return new AjaxUtils(forUserService.selectName(UserConfig.tokenUserName(request)));
    }


}
