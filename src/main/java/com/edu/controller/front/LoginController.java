package com.edu.controller.front;

import com.alibaba.fastjson.JSON;
import com.edu.service.SysUserService;
import com.edu.util.JwtUtils;
import com.edu.util.MD5Util;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

/**
 * @author yz
 * @data: 2021/10/14 17:46 星期四
 * @file : LoginController.java
 */

@Controller
public class LoginController {


    @Autowired
    private SysUserService sysUserService;

    /**
     * POST   登录请求
     *
     * @param username 用户名
     * @param password 密码MD5加密过的
     * @param status   角色(0普通用户，1管理员)
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public String loginPost(String username, String password, Integer status) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>(4);
        int n = sysUserService.countUserAdmin(username, MD5Util.inputPassToFromPass(password));
        /*
          当返回的数n=1时 ，账号密码正确，n=0时错误。
          获取当前用户的角色类型 statusStr 并判断是管理员还是普通用户
         */
        if (n == 1) {
            String statusStr = sysUserService.selectStatus(username);
            int sta = 0;
            if (statusStr.contains("管理员")) {
                sta = 1;
            }
            hashMap.put("bool", PageCodeEnum.LOGIN_SUCCESS.getBool());
            if (status == sta) {
                hashMap.put("token", JwtUtils.generateToken(username));
                hashMap.put("msg", PageCodeEnum.LOGIN_SUCCESS.getMsg());
                sysUserService.updateLogin(username, new Date());
            } else {
                hashMap.put("msg", "角色错误");
            }
        } else {
//            登录失败
            hashMap.put("bool", PageCodeEnum.LOGIN_FAIL.getBool());
            hashMap.put("msg", PageCodeEnum.LOGIN_FAIL.getMsg());
        }
        return JSON.toJSONString(hashMap);


    }

    /**
     * POST  注册请求
     *
     * @param username 用户名
     * @param password 密码  MD5加过的
     * @param home     手机号码
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public String registerPost(String username, String password, String home) {
        HashMap<String, Object> hashMap = new HashMap<>(4);
        int m = sysUserService.selectRec(username);
        if (m == 0) {
            int n = sysUserService.insertRegister(username, password, new Date(), home);
            if (n > 0) {
                hashMap.put("bool", true);
                hashMap.put("msg", "注册成功");
            } else {
                hashMap.put("bool", false);
                hashMap.put("msg", "注册失败");
            }
        } else {
            hashMap.put("bool", false);
            hashMap.put("msg", "该用户名已被使用");
        }
        System.out.println("hashMap = " + hashMap);
        return JSON.toJSONString(hashMap);
    }

}
