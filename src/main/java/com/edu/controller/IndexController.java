package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yz
 * @data: 2021/9/15 15:00 星期三
 * @file : IndexController.java
 */

@Controller
public class IndexController {


    @RequestMapping("/")
    public String indexAction(){
        return "/index.html";
    }

}
