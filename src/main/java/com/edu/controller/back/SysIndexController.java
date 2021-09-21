package com.edu.controller.back;

import com.edu.pojo.ConTents;
import com.edu.service.SysConTentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yz
 * @data: 2021/9/20 14:21 星期一
 * @file : SysIndexController.java
 */

@Controller
@RequestMapping(path = "sys")
public class SysIndexController {

    @Autowired
    private SysConTentService sysConTentService;


    /**
     * 后台页面目录
     * @return
     */
    @RequestMapping("/index.action")
    public String SysIndex(ModelMap modelMap){
        List<ConTents> list = sysConTentService.selectContents();
        System.out.println("list = " + list);

        modelMap.put("list",list);
        return "/back/sys_index.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String listAction(){
        return "sdfd";
    }
}
