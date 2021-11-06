package com.edu.controller.front;

import com.edu.config.UserConfig;
import com.edu.intercept.PassToken;
import com.edu.pojo.User;
import com.edu.service.ForLabelService;
import com.edu.service.SysUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * @author yz
 * @data: 2021/9/15 15:00 星期三
 * @file : IndexController.java
 */

@Controller

public class IndexController {


    @Autowired
    private ForLabelService forLabelService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/")
    @PassToken
    public String indexAction(@NotNull ModelMap modelMap) {
        modelMap.put("lab_list", forLabelService.forList());
        return "/index.html";
    }

    //如：修改下面的内容
    //    这里的url不能跟上面的一样

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @PassToken
    public String loginAction() {
        return "/login.html";
    }
//两个登录页面？？有一个是我自己做的，我觉得都差不多，记得调整
    //先上传 Can't commit changes due to unresolved conflicts. 什么意思啊 等下

    @RequestMapping("/login1")
    public String login1Action() {
        return "/login1.html";
    }

    @PassToken
    @RequestMapping("/zhuce")
    public String zhuceAction() {
        return "/zhuce.html";
    }


    /**
     * 前台首页
     *
     * @return
     */

    @RequestMapping("/for/index.html")
    public String forIndexAction() {
        return "/front/for_index.html";
    }


    /**
     * 个人中心页面
     *
     * @return
     */
    @RequestMapping(value = "/personal")
    public String personal() {
        return "/front/personal.html";
    }

    /**
     * 个人中心基本信息
     *
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/for/information.html")
    public String information(@NotNull ModelMap modelMap, HttpServletRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String username = UserConfig.tokenUserName(request);
        User user = sysUserService.selectBasic(username);
        user.setDate(formatter.format(user.getRegister_time()));
        modelMap.put("user", user);
        return "/front/information.html";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("/for/for_pass")
    public String forPassAction(HttpServletRequest request, @NotNull ModelMap modelMap) {
        modelMap.put("username", UserConfig.tokenUserName(request));
        return "/front/for_pass.html";
    }

    @RequestMapping("/for/for_post")
    public String forPostAction(ModelMap modelMap) {
        modelMap.put("lab_list", forLabelService.forList());
        return "/front/for_post.html";
    }


}
