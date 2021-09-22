package com.edu.controller.back;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yz
 * @data: 2021/9/22 22:39 星期三
 * @file : SysBackController.java
 */


@RestController
@RequestMapping("/data")
public class SysBackController {


    @RequestMapping("/insert")
    public String insetController(@RequestParam("x_name") String xName,                     //上级目录
                                  @RequestParam("type") String type,                      //类型
                                  @RequestParam("name") String name,                   //名字
                                  @RequestParam("competence") String competence,     //权限
                                  @RequestParam("url") String url,                //链接
                                  @RequestParam("vital") Integer vital) {           //可见

        System.out.println("xName = " + xName);
        System.out.println("type = " + type);
        System.out.println("name = " + name);
        System.out.println("url = " + url);
        System.out.println("competence = " + competence);
        System.out.println("vital = " + vital);
        HashMap<String, Object> map = new HashMap<>(4);

        boolean bool = false;
        //当选择目录且新建0时，创建父目录表
        if ("目录".equals(type)) {
            if ("0".equals(xName)) {

                System.out.println("\"ok\" = " + "ok");
                bool = true;
                map.put("msg", "目录添加成功！");
                return JSON.toJSONString(map);

            } else {
                map.put("msg", "类型与上级菜单不符");
                return JSON.toJSONString(map);
            }
        }
        //当选菜单求新建0时，报错
        if ("菜单".equals(type)) {
            if ("0".equals(xName)) {
                map.put("msg", "类型与上级菜单不符");
                return JSON.toJSONString(map);
            }
        }
        //判断目录(菜单) 名称是否为空
        if ("".equals(name)) {
            map.put("msg", "目录(菜单) 名称不能为空");
            return JSON.toJSONString(map);
        }
        map.put("msg", "ok");
        return JSON.toJSONString(map);
    }
}
