package com.edu.controller.back;

import com.edu.intercept.UserLoginToken;
import com.edu.pojo.Role;
import com.edu.service.SysRoleService;
import com.edu.util.AjaxUtils;
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
public class SysRoleController extends AjaxUtils {


    @Autowired
    private SysRoleService sysRoleService;

    @UserLoginToken(state = 1)
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "角色管理列表")
    public AjaxUtils roleList() {
        List<Role> roleList = sysRoleService.listRole();
        return new AjaxUtils(0, roleList);
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
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ro_id", value = "角色id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "ro_have", value = "拥有的权限", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ro_describe", value = "具体描述", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ro_status", value = "角色状态", required = false, dataType = "Integer", paramType = "query"),
    })
    @ApiOperation(value = "角色管理内容的修改")
    public AjaxUtils roleEdit(@RequestParam("ro_id") Integer roleId,
                              @RequestParam("ro_have") String roHave,
                              @RequestParam("ro_describe") String roDescribe,
                              @RequestParam("ro_status") Integer roStatus) {
        Role role = new Role(roleId, null, null, roStatus, roHave, roDescribe);
        int n = sysRoleService.edit(role);
        if (n > 0) {
            return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } else {
            return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), PageCodeEnum.MODIFY_FAIL.getMsg());
        }
    }

    /**
     * 角色管理内容的删除
     *
     * @param roleId
     * @return
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "角色管理内容的删除")
    @ApiImplicitParam(name = "ro_id", value = "角色id", required = true, dataType = "Integer", paramType = "query")
    public AjaxUtils roleDle(@RequestParam("ro_id") Integer roleId) {
        int n = sysRoleService.dle(roleId);
        if (n > 0) {
            return new AjaxUtils(PageCodeEnum.REMOVE_SUCCESS.getBool(), PageCodeEnum.REMOVE_SUCCESS.getMsg());
        } else {
            return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg());
        }
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
    @UserLoginToken(state = 1)
    @RequestMapping("/add")
    public AjaxUtils roleAdd(@RequestParam("status") String status,
                             @RequestParam("ro_have") String roHave,
                             @RequestParam("ro_describe") String roDescribe,
                             @RequestParam("ro_status") Integer roStatus) {
        HashMap<String, Object> map = new HashMap<>(2);
        Role role = new Role(null, null, status, roStatus, roHave, roDescribe);
        int n = sysRoleService.add(role);
        if (n > 0) {
            return new AjaxUtils(PageCodeEnum.ADD_SUCCESS.getBool(), PageCodeEnum.ADD_SUCCESS.getMsg());
        } else {
            return new AjaxUtils(PageCodeEnum.ADD_FAIL.getBool(), PageCodeEnum.ADD_FAIL.getMsg());
        }
    }


}
