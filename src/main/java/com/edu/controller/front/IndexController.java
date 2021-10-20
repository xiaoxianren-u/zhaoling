package com.edu.controller.front;

import com.edu.intercept.PassToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yz
 * @data: 2021/9/15 15:00 星期三
 * @file : IndexController.java
 */

@Controller
public class IndexController {


    @RequestMapping("/")
    @PassToken
    public String indexAction() {
        return "/index.html";
    }

    //如：修改下面的内容
    //    这里的url不能跟上面的一样
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAction() {
        return "/login.html";
    }
//两个登录页面？？有一个是我自己做的，我觉得都差不多，记得调整
    //先上传 Can't commit changes due to unresolved conflicts. 什么意思啊 等下

    @RequestMapping("/login1")
    public String loginaAction() {
        return "/login1.html";
    }

    @PassToken
    @RequestMapping("/zhuce")
    public String zhuceAction() {
        return "/zhuce.html";
    }
//sdf

}
