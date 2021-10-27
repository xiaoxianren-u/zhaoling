package com.edu.controller.back;

import com.edu.config.UserConfig;
import com.edu.intercept.OperateSer;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.User;
import com.edu.service.SysUserService;
import com.edu.util.AjaxUtils;
import com.edu.util.MD5Util;
import com.edu.util.PageCodeEnum;
import com.edu.util.UploadUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * @author yz
 * @data: 2021/10/2 15:26 星期六
 * @file : SysUserController.java
 */

/**
 * 后后对用户的操作
 *
 * @author yangzhan
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends AjaxUtils {


    @Autowired
    private SysUserService sysUserService;


    /**
     * 管理员列表
     *
     * @return
     */
    @OperateSer(operationName = "select操作", operationType = "查询管理列表")
    @RequestMapping(value = "/adminlist", method = RequestMethod.GET)
    @UserLoginToken(state = 1)
    public AjaxUtils adminList(@RequestParam("page") Integer page,
                               @RequestParam("limit") Integer limit,
                               @RequestParam("username") String username) {
        page = page > 1 ? limit * (page - 1) : 0;
        List<User> list = sysUserService.selectAdminList(page, limit, username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = sysUserService.adminCount(username);
        return new AjaxUtils(n, list);
    }

    /**
     * 用户列表   实现分页
     *
     * @param page
     * @param limit
     * @return
     */
    @OperateSer(operationName = "select操作", operationType = "查询用户列表")
    @RequestMapping("/userlist")
    @UserLoginToken(state = 1)
    public AjaxUtils userList(@RequestParam("page") Integer page,
                              @RequestParam("limit") Integer limit,
                              @RequestParam("username") String username) {
        page = page > 1 ? limit * (page - 1) : 0;
        List<User> list = sysUserService.selectUserList(page, limit, username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = sysUserService.selectCount(username);
        return new AjaxUtils(n, list);
    }


    /**
     * 用户黑名单列表   实现分页
     *
     * @param page
     * @param limit
     * @return
     */
    @OperateSer(operationName = "select操作", operationType = "查询用户黑名单列表")
    @RequestMapping("/blacklist")
    @UserLoginToken(state = 1)
    public AjaxUtils blackList(@RequestParam("page") Integer page,
                               @RequestParam("limit") Integer limit,
                               @RequestParam("username") String username) {
        page = page > 1 ? limit * (page - 1) : 0;
        List<User> list = sysUserService.selectBlackList(page, limit, username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = sysUserService.selectblackCount(username);
        return new AjaxUtils(n, list);
    }


    /**
     * 图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    @ResponseBody
    @UserLoginToken(state = 1)
    public AjaxUtils updateImager(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String imagePath = UploadUtils.upload(file, request);
        System.out.println("image = " + imagePath);
        if (imagePath != null) {
            return new AjaxUtils(true, "上传成功", imagePath);
        } else {
            return new AjaxUtils(false, "上传失败");
        }
    }

    /**
     * 基本资料修改
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update/basic", method = RequestMethod.POST)
    @UserLoginToken(state = 1)
    @ResponseBody
    public AjaxUtils updateBasic(@RequestBody User user, HttpServletRequest request) {
        user.setUser_name(UserConfig.tokenUserName(request));
        try {
            sysUserService.updateBasic(user);
            return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), PageCodeEnum.MODIFY_FAIL.getMsg());
        }
    }

    /**
     * 后台修改密码
     *
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    public AjaxUtils passWord(@NotNull @RequestBody User user, HttpServletRequest request) {
        user.setUser_name(UserConfig.tokenUserName(request));
        user.setPass_word(MD5Util.inputPassToFromPass(user.getPass_word()));
        user.setName(MD5Util.inputPassToFromPass(user.getName()));
        String password = sysUserService.selectPassword(user.getUser_name());
        if (Objects.equals(password, user.getPass_word())) {
            try {
                sysUserService.updatePass(user.getUser_name(), user.getName());
                return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
            } catch (Exception e) {
                e.printStackTrace();
                return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), PageCodeEnum.MODIFY_FAIL.getMsg());
            }
        } else {
            return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), "当前密码不对");
        }
    }

    /**
     * 修改用户表角色类型或拉黑
     *
     * @param user
     * @return
     */

    @OperateSer(operationName = "Update", operationType = "修改用户表角色类型或拉黑")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/upUser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxUtils upUser(@NotNull @RequestBody User user) {
        user.setPass_word(MD5Util.inputPassToFromPass(user.getPass_word()));
        return sysUserService.upUser(user);
    }

    /**
     * 修改黑名单的角色类型或拉黑
     *
     * @param user
     * @return
     */
    @OperateSer(operationName = "Update", operationType = "修改黑名单的角色类型或拉黑")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/upUserBlack", method = RequestMethod.POST)
    @ResponseBody
    public AjaxUtils upUserBlack(@NotNull @RequestBody User user) {
        user.setPass_word(MD5Util.inputPassToFromPass(user.getPass_word()));
        return sysUserService.upUserBlack(user);
    }

    /**
     * 修改黑名单的角色类型或拉黑
     *
     * @param user
     * @return
     */
    @OperateSer(operationName = "Update", operationType = "修改黑名单的角色类型或拉黑")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/upUserNot", method = RequestMethod.POST)
    @ResponseBody
    public AjaxUtils upUserNot(@NotNull @RequestBody User user) {
        user.setPass_word(MD5Util.inputPassToFromPass(user.getPass_word()));
        return sysUserService.upUserNot(user);
    }


    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @OperateSer(operationName = "delete", operationType = "删除用户")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/dlUser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxUtils dlUser(@NotNull @RequestBody User user) {
        user.setPass_word(MD5Util.inputPassToFromPass(user.getPass_word()));
        return sysUserService.dlUser(user);
    }


}
