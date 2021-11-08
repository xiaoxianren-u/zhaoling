package com.edu.controller.front;

import com.edu.config.UserConfig;
import com.edu.intercept.PassToken;
import com.edu.pojo.Post;
import com.edu.pojo.User;
import com.edu.service.ForLabelService;
import com.edu.service.ForPostService;
import com.edu.service.ForUserService;
import com.edu.service.SysUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private ForUserService forUserService;

    @Autowired
    private ForPostService forPostService;

    @RequestMapping("/")
    @PassToken
    public String indexAction(@NotNull ModelMap modelMap) {
        modelMap.put("lab_list", forLabelService.forList());

        return "/index.html";
    }

    /**
     * 寻物
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/lost")
    public String lostAction(@NotNull ModelMap modelMap) {
        modelMap.put("lab_list", forLabelService.forList());
        return "/front/for_lost.html";
    }

    /**
     * 招领
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/find")
    public String findAction(@NotNull ModelMap modelMap) {
        modelMap.put("lab_list", forLabelService.forList());
        return "/front/for_find.html";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @PassToken
    public String loginAction() {
        return "/login.html";
    }


    @RequestMapping("/login1")
    public String login1Action() {
        return "/login1.html";
    }

    /**
     * @return
     */
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
    public String personal(@NotNull ModelMap modelMap, HttpServletRequest request) {
        String username = UserConfig.tokenUserName(request);
        User user = forUserService.selectImage(username);
        modelMap.put("username", user.getUser_name());
        modelMap.put("image", user.getUser_image());
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
    public String forPostAction(@NotNull ModelMap modelMap) {
        modelMap.put("lab_list", forLabelService.forList());
        return "/front/for_post.html";
    }


    /**
     * 显示单个内容
     *
     * @param t
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String textAction(@RequestParam("t") Integer t, @NotNull ModelMap modelMap) {
        Post post = forPostService.selectPo(t);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        modelMap.put("post", post);
        modelMap.put("data1", formatter.format(post.getPost_found_time()));
        modelMap.put("data2", formatter.format(post.getPost_time()));
        return "/front/text";
    }


}
