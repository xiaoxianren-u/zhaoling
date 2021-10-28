package com.edu.controller.front;

import com.alibaba.fastjson.JSON;
import com.edu.intercept.PassToken;
import com.edu.pojo.User;
import com.edu.service.SysRoleService;
import com.edu.service.SysUserService;
import com.edu.util.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yz
 * @data: 2021/10/14 17:46 星期四
 * @file : LoginController.java
 */

@Controller
@RequestMapping
public class LoginController extends AjaxUtils {


    /**
     * 用户业务
     */
    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private SysRoleService sysRoleService;

    /**
     * POST   登录请求
     *
     * @param user user.getUser_name() 用户名
     *             user.getPass_word() 密码MD5加密过的
     *             status   角色(0普通用户，1管理员)
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    @PassToken
    public String loginPost(@NotNull @RequestBody User user, HttpServletResponse response, @NotNull HttpServletRequest request) {

        request.getSession().setAttribute("name", user.getUser_name());
        int status = Integer.parseInt(user.getStatus());
        HashMap<String, Object> hashMap = new HashMap<String, Object>(4);
        int n = sysUserService.countUserAdmin(user.getUser_name(), MD5Util.inputPassToFromPass(user.getPass_word()));
        /*
          当返回的数n=1时 ，账号密码正确，n=0时错误。
          获取当前用户的角色类型 statusStr 并判断是管理员还是普通用户
         */
        if (n == 1) {
            /*
             * 获取用户的就是，判断他是否为管理员，
             * 如果是管理员则将sta=1，不是sta=0
             */
            String statusStr = sysUserService.selectStatus(user.getUser_name());
            int sta = 0;
            if (statusStr.contains("管理员")) {
                sta = 1;
            }
            /*
             * 获取该用户的状态，判断他是否已经被拉黑 ，st=0表示正常 ，st=1表示黑名单
             */
            int st = sysRoleService.selectStatus(statusStr);
            if (st == 1) {
                hashMap.put("bool", PageCodeEnum.LOGIN_FAIL.getBool());
                hashMap.put("msg", "该角色已被停止登录系统了");
                request.getSession().setAttribute("log", "登录失败");
                return JSON.toJSONString(hashMap);
            }

            if (status == sta) {
                hashMap.put("bool", PageCodeEnum.LOGIN_SUCCESS.getBool());
                /*
                 * 生成token用于校验是否登陆，并设置权限，
                 * 当sta=1时，只有可以访问 @UserLoginToken(state = "1")的其他的不能访问 管理员权限
                 * 当sta=0时，同理
                 */
                String token = JwtUtils.generateToken(user.getUser_name(), sta, user.getUser_id());
                hashMap.put("key", sta);
                hashMap.put("token", token);
                hashMap.put("msg", PageCodeEnum.LOGIN_SUCCESS.getMsg());
                request.getSession().setAttribute("log", "登录成功");
                response.setHeader("Cookie", token);
                /*修改用户最后的登录的时间*/
                sysUserService.updateLogin(user.getUser_name(), new Date());
            } else {
                request.getSession().setAttribute("log", "登录失败");
                hashMap.put("bool", PageCodeEnum.LOGIN_FAIL.getBool());
                hashMap.put("msg", "角色错误");
            }
        } else {
            //登录失败
            request.getSession().setAttribute("log", "登录失败");
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
    @PassToken
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


    /**
     * 生成验证码
     *
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/getCode")
    @ResponseBody
    public void getCode(HttpServletRequest req, HttpServletResponse resp) {
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtils.generateCodeAndPic();

        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证验证输入是否正确
     *
     * @param yzm
     * @param request
     * @return
     */
    @RequestMapping(value = "/yz", method = RequestMethod.GET)
    @ResponseBody
    public Object yz(String yzm, HttpServletRequest request) {
        //字符串全部转为小写
        yzm = yzm.toLowerCase();
        String yzm1 = request.getSession().getAttribute("code").toString().toLowerCase();
        if (yzm.equals(yzm1)) {
            return new AjaxUtils(true);
        } else {
            return new AjaxUtils(false);
        }
    }

}
