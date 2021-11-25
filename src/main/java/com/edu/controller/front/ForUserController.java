package com.edu.controller.front;

import com.edu.config.UserConfig;
import com.edu.pojo.User;
import com.edu.service.ForUserService;
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
import java.util.Objects;

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

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public AjaxUtils getName(@NotNull HttpServletRequest request) {

        return new AjaxUtils(forUserService.selectName(UserConfig.tokenUserName(request)));
    }


    /**
     * 图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    public AjaxUtils updateImager(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String imagePath = UploadUtils.upload(file, request, "image");

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
    public AjaxUtils updateBasic(@NotNull @RequestBody User user, HttpServletRequest request) {


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
    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    public AjaxUtils passWord(@NotNull @RequestBody User user, HttpServletRequest request) {

        user.setUser_name(UserConfig.tokenUserName(request));
        System.out.println("======================================");
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


}
