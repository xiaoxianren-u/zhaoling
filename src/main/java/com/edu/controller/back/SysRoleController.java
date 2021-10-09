package com.edu.controller.back;

import com.alibaba.fastjson.JSON;
import com.edu.pojo.Role;
import com.edu.service.SysRoleService;
import com.edu.util.PageCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author yz
 * @data: 2021/9/29 15:36 星期三
 * @file : SysRoleController.java
 */

@RestController
@RequestMapping("/role")
@Api(tags = "SysRoleController", description = "角色管理")
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "角色管理列表")
    public String roleList() {
        List<Role> roleList = sysRoleService.listRole();
        System.out.println("roleList = " + roleList);
        String s = "{\"code\":0,\"msg\":\"\",\"count\":" + 0 + ",\"data\":" + JSON.toJSONString(roleList) + "}";
        return s;
    }


    /**
     * 角色管理内容的修改
     *
     * @param roleId
     * @param roHave
     * @param roDescribe
     * @param roStatus
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ro_id", value = "角色id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "ro_have", value = "拥有的权限", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ro_describe", value = "具体描述", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ro_status", value = "角色状态", required = false, dataType = "Integer", paramType = "query"),
    })
    @ApiOperation(value = "角色管理内容的修改")
    public String roleEdit(@RequestParam("ro_id") Integer roleId,
                           @RequestParam("ro_have") String roHave,
                           @RequestParam("ro_describe") String roDescribe,
                           @RequestParam("ro_status") Integer roStatus) {

        HashMap<String, Object> map = new HashMap<>(2);
        Role role = new Role(roleId, null, null, roStatus, roHave, roDescribe);
        System.out.println("role = " + role);
        int n = sysRoleService.edit(role);
        if (n > 0) {
            map.put("bool", PageCodeEnum.MODIFY_SUCCESS.getBool());
            map.put("msg", PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } else {
            map.put("bool", PageCodeEnum.MODIFY_FAIL.getBool());
            map.put("msg", PageCodeEnum.MODIFY_FAIL.getMsg());
        }
        return JSON.toJSONString(map);
    }

    /**
     * 角色管理内容的删除
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "角色管理内容的删除")
    @ApiImplicitParam(name = "ro_id", value = "角色id", required = true, dataType = "Integer", paramType = "query")
    public String roleDle(@RequestParam("ro_id") Integer roleId) {
        HashMap<String, Object> map = new HashMap<>(2);
        int n = sysRoleService.dle(roleId);
        if (n > 0) {
            map.put("bool", PageCodeEnum.REMOVE_SUCCESS.getBool());
            map.put("msg", PageCodeEnum.REMOVE_SUCCESS.getMsg());
        } else {
            map.put("bool", PageCodeEnum.REMOVE_FAIL.getBool());
            map.put("msg", PageCodeEnum.REMOVE_FAIL.getMsg());
        }
        return JSON.toJSONString(map);
    }


    /**
     * 添加添加新的角色
     *
     * @param status
     * @param roHave
     * @param roDescribe
     * @param roStatus
     * @return
     */
    @RequestMapping("/add")
    public String roleAdd(@RequestParam("status") String status,
                          @RequestParam("ro_have") String roHave,
                          @RequestParam("ro_describe") String roDescribe,
                          @RequestParam("ro_status") Integer roStatus) {


        System.out.println("status = " + status);
        System.out.println("roHave = " + roHave);
        System.out.println("roDescribe = " + roDescribe);
        System.out.println("roStatus = " + roStatus);
        HashMap<String, Object> map = new HashMap<>(2);
        Role role = new Role(null, null, status, roStatus, roHave, roDescribe);
        System.out.println("role = " + role);

        int n = sysRoleService.add(role);
//        System.out.println("n = " + n);
        if (n > 0) {
            map.put("bool", PageCodeEnum.ADD_SUCCESS.getBool());
            map.put("msg", PageCodeEnum.ADD_SUCCESS.getMsg());
        } else {
            map.put("bool", PageCodeEnum.ADD_FAIL.getBool());
            map.put("msg", PageCodeEnum.ADD_FAIL.getMsg());
        }
        return JSON.toJSONString(map);
    }


}
