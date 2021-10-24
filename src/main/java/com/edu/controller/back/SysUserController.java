package com.edu.controller.back;

import com.alibaba.fastjson.JSON;
import com.edu.config.UserConfig;
import com.edu.intercept.OperateSer;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.User;
import com.edu.service.SysUserService;
import com.edu.util.MD5Util;
import com.edu.util.PageCodeEnum;
import com.edu.util.UploadUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;


    /**
     * 管理员列表
     *
     * @return
     */
    @OperateSer(operationName = "select操作", operationType = "查询管理列表")
    @RequestMapping("/adminlist")
    @UserLoginToken(state = 1)
    public String adminList() {
        List<User> list = sysUserService.selectAdminList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = list.size();
        return "{\"code\":0,\"msg\":\"\",\"count\":" + n + ",\"data\":" + JSON.toJSONString(list) + "}";
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
    public String userList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        page = page > 1 ? limit * (page - 1) : 0;
        List<User> list = sysUserService.selectUserList(page, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = sysUserService.selectCount();
        return "{\"code\":0,\"msg\":\"\",\"count\":" + n + ",\"data\":" + JSON.toJSONString(list) + "}";
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
    public String blackList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        page = page > 1 ? limit * (page - 1) : 0;
        List<User> list = sysUserService.selectBlackList(page, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = sysUserService.selectblackCount();
        return "{\"code\":0,\"msg\":\"\",\"count\":" + n + ",\"data\":" + JSON.toJSONString(list) + "}";
    }


    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    @ResponseBody
    @UserLoginToken(state = 1)
    public String updateImager(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        HashMap<String, Object> hashMap = new HashMap<>(3);
        System.out.println("file = " + file);

        String imagePath = UploadUtils.upload(file, request);
        System.out.println("image = " + imagePath);
        if (imagePath != null) {
            hashMap.put("bool", true);
            hashMap.put("msg", "上传成功");
            hashMap.put("data", imagePath);
        } else {
            hashMap.put("bool", false);
            hashMap.put("msg", "上传失败");
        }
        return JSON.toJSONString(hashMap);
    }

    /**
     * 基本
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update/basic", method = RequestMethod.POST)
    @UserLoginToken(state = 1)
    @ResponseBody
    public String updateBasic(@RequestBody User user, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>(3);
        System.out.println("user = " + user);
        user.setUser_name(UserConfig.tokenUserName(request));
        try {
            sysUserService.updateBasic(user);
            hashMap.put("bool", PageCodeEnum.MODIFY_SUCCESS.getBool());
            hashMap.put("msg", PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("bool", PageCodeEnum.MODIFY_FAIL.getBool());
            hashMap.put("msg", PageCodeEnum.MODIFY_FAIL.getMsg());
        }
        return JSON.toJSONString(hashMap);
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
    public String passWord(@NotNull @RequestBody User user, HttpServletRequest request) {
        user.setUser_name(UserConfig.tokenUserName(request));
        HashMap<String, Object> hashMap = new HashMap<>();
        user.setPass_word(MD5Util.inputPassToFromPass(user.getPass_word()));
        user.setName(MD5Util.inputPassToFromPass(user.getName()));
        String password = sysUserService.selectPassword(user.getUser_name());
        if (Objects.equals(password, user.getPass_word())) {
            try {
                sysUserService.updatePass(user.getUser_name(), user.getName());
                hashMap.put("bool", PageCodeEnum.MODIFY_SUCCESS.getBool());
                hashMap.put("msg", PageCodeEnum.MODIFY_SUCCESS.getMsg());
            } catch (Exception e) {
                hashMap.put("bool", PageCodeEnum.MODIFY_FAIL.getBool());
                hashMap.put("msg", PageCodeEnum.MODIFY_FAIL.getMsg());
                e.printStackTrace();
            }
        } else {
            hashMap.put("bool", PageCodeEnum.MODIFY_FAIL.getBool());
            hashMap.put("msg", "当前密码不对");
        }
        return JSON.toJSONString(hashMap);
    }
}
