package com.edu.controller.back;

import com.edu.config.UserConfig;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.ConTents;
import com.edu.pojo.User;
import com.edu.service.SysConTentService;
import com.edu.service.SysLabelService;
import com.edu.service.SysUserService;
import com.edu.util.AjaxUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 14:21 星期一
 * @file : SysIndexController.java
 */

/**
 * 后台页面的请求
 *
 * @author yangzhan
 */

@Controller
@RequestMapping(path = "sys")
public class SysIndexController extends AjaxUtils {

    @Autowired
    private SysConTentService sysConTentService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLabelService sysLabelService;

    /**
     * 后台页面目录
     */

    @UserLoginToken(state = 1)
    @RequestMapping(value = "/index.action", method = RequestMethod.GET)
    public String sysIndex(@NotNull ModelMap modelMap, HttpServletRequest request) {
        List<ConTents> list = sysConTentService.selectContents_sys();
        String username = UserConfig.tokenUserName(request);
        User user = sysUserService.selectBasic(username);
        modelMap.put("user", user);
        modelMap.put("list", list);
        return "back/sys_index";
    }

    /**
     * 菜单管理
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/menu.action", method = RequestMethod.GET)
    public String listAction(@NotNull ModelMap modelMap) {

        List<ConTents> list = sysConTentService.selectContents();
        modelMap.put("list", list);
        return "back/menu";
    }

    /**
     * 角色管理
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/role.action", method = RequestMethod.GET)
    public String roleAction(ModelMap modelMap) {
        return "back/role";
    }

    /**
     * 后台管理员
     */


    @UserLoginToken(state = 1)
    @RequestMapping(value = "/administrator.action", method = RequestMethod.GET)
    public String administratorAction() {
        return "back/administrator";
    }

    /**
     * 用户列表
     */

    @UserLoginToken(state = 1)
    @RequestMapping(value = "/user.action", method = RequestMethod.GET)
    public String userAction() {
        return "back/user";
    }

    /**
     * 用户列表
     */

    @UserLoginToken(state = 1)
    @RequestMapping(value = "/black.action", method = RequestMethod.GET)
    public String blackAction() {
        return "back/black";
    }

    /**
     * 用户登录日志
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/log.action", method = RequestMethod.GET)
    public String logAction() {
        return "back/log";
    }

    /**
     * 用户操作日志
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/operate.action", method = RequestMethod.GET)
    public String operateAction() {
        return "back/operate";
    }


    /**
     * 基本信息页面
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/basic.action", method = RequestMethod.GET)
    public String basicAction(@NotNull ModelMap modelMap, @NotNull HttpServletRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String username = UserConfig.tokenUserName(request);
        User user = sysUserService.selectBasic(username);
        user.setDate(formatter.format(user.getRegister_time()));
        modelMap.put("user", user);
        return "back/basic";
    }

    /**
     * 修改密码页面
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/upPass.action", method = RequestMethod.GET)
    public String upPassAction(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("username", UserConfig.tokenUserName(request));
        return "back/uppass";
    }

    /**
     * 公告页面
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/anNoun.action", method = RequestMethod.GET)
    public String anNounCementAction() {
        return "back/announcement";
    }

    /**
     * 标签页面
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/label.action", method = RequestMethod.GET)
    public String labelAction() {
        return "back/label";
    }

    /**
     * 了解招物
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/welcome.action", method = RequestMethod.GET)
    public String findAction() {
        return "back/welcome";
    }


    /**
     * 帖子管理
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/postman.action", method = RequestMethod.GET)
    public String postMan(ModelMap modelMap) {


        modelMap.put("lab_list", sysLabelService.list());
        return "back/post_man";
    }

}
