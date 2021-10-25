package com.edu.controller.back;

import com.alibaba.fastjson.JSON;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.ConTents;
import com.edu.pojo.mulu;
import com.edu.service.SysMenuService;
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

/**
 * @author yz
 * @data: 2021/9/22 22:39 星期三
 * @file : SysBackController.java
 */


@RestController
@RequestMapping("/menu")
@Api(tags = "SysMenuController", description = "菜单管理")
public class SysMenuController extends AjaxUtils {

    /**
     * 父目录
     */
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 目录以及菜单的添加
     *
     * @param xName      //上级目录
     * @param type       //类型
     * @param name       //名字
     * @param competence //权限
     * @param url        //链接
     * @param vital      //可见
     * @return
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/insert", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "目录以及菜单的添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x_name", value = "上级目录", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "目录或菜单名字", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "competence", value = "权限", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "菜单连接", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "vital", value = "(1、隐藏，0、显示)", required = true, dataType = "Integer", paramType = "query")
    })
    public AjaxUtils insetController(@RequestParam("x_name") Integer xName,                     //上级目录
                                     @RequestParam("type") String type,                      //类型
                                     @RequestParam("name") String name,                   //名字
                                     @RequestParam("competence") String competence,     //权限
                                     @RequestParam("url") String url,                //链接
                                     @RequestParam("vital") Integer vital) {           //可见
        HashMap<String, Object> map = new HashMap<>(4);

        //判断目录(菜单) 名称是否为空
        if ("".equals(name)) {
            return new AjaxUtils(false, "目录(菜单) 名称不能为空");
        }
        //当选择目录且新建0时，创建父目录表
        if ("目录".equals(type)) {
            if (0 == xName) {
                mulu m = new mulu();
                m.setM_competence(competence);
                m.setM_url(url);
                m.setM_vital(vital);
                m.setM_name(name);
                int n = sysMenuService.insert(m);
                return new AjaxUtils(n > 0 ? PageCodeEnum.ADD_SUCCESS.getBool() : PageCodeEnum.ADD_FAIL.getBool(), n > 0 ? PageCodeEnum.ADD_SUCCESS.getMsg() : PageCodeEnum.ADD_FAIL.getMsg());
            } else {
                map.put("bool", false);
                map.put("msg", PageCodeEnum.SUB_MENU_EXISTS_M.getMsg());
                return new AjaxUtils(false, PageCodeEnum.SUB_MENU_EXISTS_M.getMsg());
            }
        }
        //当选菜单求新建0时，报错
        if ("菜单".equals(type)) {
            if (0 == xName) {
                map.put("bool", false);
                map.put("msg", PageCodeEnum.SUB_MENU_EXISTS_M.getMsg());
                return new AjaxUtils(false, PageCodeEnum.SUB_MENU_EXISTS_M.getMsg());
            }
        }
        /**
         * 菜单的添加
         */
        ConTents conTents = new ConTents();
        conTents.setCon_number(xName);
        conTents.setCon_competence(competence);
        conTents.setCon_name(name);
        conTents.setCon_url(url);
        conTents.setCon_vital(vital);
        int n = sysMenuService.inset_Con(conTents);
        return new AjaxUtils(n > 0 ? PageCodeEnum.ADD_SUCCESS.getBool() : PageCodeEnum.ADD_FAIL.getBool(), n > 0 ? PageCodeEnum.ADD_SUCCESS.getMsg() : PageCodeEnum.ADD_FAIL.getMsg());
    }


    /**
     * 获取要修改指定目录的字段
     *
     * @param mNumber
     * @return
     */
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/m_update", method = RequestMethod.GET)
    @ApiOperation(value = "获取要修改指定目录的字段")
    @ApiImplicitParam(name = "m_number", value = "目录编号", required = true, dataType = "Integer", paramType = "query")
    public AjaxUtils mUpdate(@RequestParam("m_number") Integer mNumber) {
        mulu m = sysMenuService.selectMone(mNumber);
        if (m != null) {
            return new AjaxUtils(true, m);
        } else {
            return new AjaxUtils(false, "服务器出错500！！！");
        }
    }


    /**
     * 目录修改
     *
     * @param mName
     * @param mVital
     * @param mNumber
     * @return
     */
    @UserLoginToken(state = 1)
    @ApiOperation(value = "目录修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "m_name", value = "父目录名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "m_vital", value = "父目录可见（0显示，1隐藏）", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "m_number", value = "父目录编号", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/m_update/data", method = RequestMethod.GET)
    public AjaxUtils mUpdateData(@RequestParam("m_name") String mName,
                                 @RequestParam("m_vital") Integer mVital,
                                 @RequestParam("m_number") Integer mNumber) {
        mulu m = new mulu(null, mName, mNumber, null, null, mVital, null, null, null);
        int n = sysMenuService.updateM_nu(m);
        if (n > 0) {
            return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } else {
            return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), PageCodeEnum.MODIFY_FAIL.getMsg());
        }
    }


    /**
     * 获取要修改指定菜单的字段
     *
     * @param conNumber
     * @return
     */
    @ApiOperation(value = "获取要修改指定菜单的字段")
    @ApiImplicitParam(name = "conNumber", value = "菜单编号", required = true, dataType = "Integer", paramType = "query")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/con_update", method = RequestMethod.GET)
    public AjaxUtils conUpdate(@RequestParam("con_number") Integer conNumber) {
        ConTents conTents = sysMenuService.selectConone(conNumber);
        if (conTents != null) {
            return new AjaxUtils(true, conTents);
        } else {
            return new AjaxUtils(false, "服务器出错500！！！");
        }
    }

    /**
     * 修改菜单
     *
     * @param conName
     * @param conVital
     * @param conCompetence
     * @param conUrl
     * @param conId
     * @return
     */
    @ApiOperation(value = "修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "con_name", value = "目录名字", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "con_vital", value = "目录可见（0显示，1隐藏)", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "con_competence", value = "目录权限", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "con_url", value = "目录链接", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "con_id", value = "目录id", required = true, dataType = "Integer", paramType = "query")
    })
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/con_update/data", method = RequestMethod.GET)
    public AjaxUtils conUpdateData(@RequestParam("con_name") String conName,
                                   @RequestParam("con_vital") Integer conVital,
                                   @RequestParam("con_competence") String conCompetence,
                                   @RequestParam("con_url") String conUrl,
                                   @RequestParam("con_id") Integer conId) {
        ConTents conTents = new ConTents(conId, conName, conUrl, null, null, conVital, conCompetence, null);
        int n = sysMenuService.updateCon_nu(conTents);
        if (n > 0) {
            return new AjaxUtils(PageCodeEnum.MODIFY_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } else {
            return new AjaxUtils(PageCodeEnum.MODIFY_FAIL.getBool(), PageCodeEnum.MODIFY_FAIL.getMsg());
        }
    }


    /**
     * 删除菜单
     *
     * @param conId
     * @return
     */
    @ApiOperation(value = "删除子目录")
    @ApiImplicitParam(name = "con_id", value = "子目录id", required = true, dataType = "Integer", paramType = "query")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/con_delete", method = RequestMethod.GET)
    public String conDelete(@RequestParam("con_id") Integer conId) {
        HashMap<String, Object> map = sysMenuService.conDeleTe(conId);
        return JSON.toJSONString(map);
    }

    /**
     * 删除目录
     *
     * @param mNumber
     * @return
     */
    @UserLoginToken(state = 1)
    @ApiOperation(value = "删除父目录")
    @ApiImplicitParam(name = "m_number", value = "父目录id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/m_delete", method = RequestMethod.GET)
    public AjaxUtils mDelete(@RequestParam("m_number") Integer mNumber) {
        int n = sysMenuService.mDeleTe(mNumber);
        if (n > 0) {
            return new AjaxUtils(PageCodeEnum.REMOVE_SUCCESS.getBool(), PageCodeEnum.REMOVE_SUCCESS.getMsg());
        } else {
            return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg());
        }
    }
}
