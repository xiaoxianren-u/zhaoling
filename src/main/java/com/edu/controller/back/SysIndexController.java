package com.edu.controller.back;

import com.edu.intercept.UserLoginToken;
import com.edu.pojo.ConTents;
import com.edu.service.SysConTentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 14:21 星期一
 * @file : SysIndexController.java
 */

/**
 * 后台页面的请求
 */

@Controller
@RequestMapping(path = "sys")

public class SysIndexController {

    @Autowired
    private SysConTentService sysConTentService;

    /**
     * 后台页面目录
     *
     * @return
     */

    @UserLoginToken
    @RequestMapping("/index.action")
    public String sysIndex(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        List<ConTents> list = sysConTentService.selectContents_sys();
        modelMap.put("list", list);
        return "/back/sys_index";
    }

    /**
     * 菜单管理
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/menu.action", method = RequestMethod.GET)
    public String listAction(ModelMap modelMap) {

        List<ConTents> list = sysConTentService.selectContents();
        modelMap.put("list", list);
        return "/back/menu";
    }

    /**
     * 角色管理
     *
     * @param modelMap
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/role.action", method = RequestMethod.GET)
    public String roleAction(ModelMap modelMap) {

        return "/back/role";
    }

    /**
     * 后台管理员
     *
     * @return
     */


    @UserLoginToken
    @RequestMapping(value = "/administrator.action", method = RequestMethod.GET)
    public String administratorAction() {
        return "/back/administrator";
    }

    /**
     * 用户列表
     *
     * @return
     */

    @UserLoginToken
    @RequestMapping(value = "/user.action", method = RequestMethod.GET)
    public String userAction() {
        return "/back/user";
    }

    /**
     * 用户列表
     *
     * @return S
     */

    @UserLoginToken
    @RequestMapping(value = "/black.action", method = RequestMethod.GET)
    public String blackAction() {
        return "/back/black";
    }

    /**
     * 用户登录日志
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/log.action", method = RequestMethod.GET)
    public String logAction() {
        return "/back/log";
    }

    /**
     * 用户操作日志
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/operate.action", method = RequestMethod.GET)
    public String operateAction() {
        return "/back/operate";
    }

}
