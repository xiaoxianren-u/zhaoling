package com.edu.controller.back;

import com.alibaba.fastjson.JSON;
import com.edu.pojo.User;
import com.edu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

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
    @RequestMapping("/adminlist")
//    @UserLoginToken
    public String adminList() {
        List<User> list = sysUserService.selectAdminList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (User s : list) {
            s.setDate(formatter.format(s.getRegister_time()));
        }
        int n = list.size();
        System.out.println("list = " + list);
        return "{\"code\":0,\"msg\":\"\",\"count\":" + n + ",\"data\":" + JSON.toJSONString(list) + "}";
    }

}
