package com.edu.controller.back;

import com.edu.intercept.OperateSer;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.Label;
import com.edu.service.SysLabelService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yz
 * @data: 2021/10/27 20:01 星期三
 * @file : SysLabelController.java
 */

@RestController
@RequestMapping(value = "/label")
public class SysLabelController extends AjaxUtils {


    @Autowired
    private SysLabelService sysLabelService;

    /**
     * 标签列表
     *
     * @return
     */

    @UserLoginToken(state = 1)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxUtils labList() {
        return new AjaxUtils(0, sysLabelService.list());
    }


    /**
     * "删除帖子标签
     *
     * @param lab_id
     * @return
     */
    @OperateSer(operationType = "delete", operationName = "删除帖子标签")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public AjaxUtils labelDel(@RequestParam(value = "lab_id") Integer lab_id) {
        return sysLabelService.del(lab_id);
    }

    /**
     * 修改帖子标签
     *
     * @param label
     * @return
     */
    @OperateSer(operationType = "update", operationName = "修改帖子标签")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public AjaxUtils labelUp(@RequestBody Label label) {
        return sysLabelService.up(label);

    }

    /**
     * 添加帖子标签
     *
     * @param label
     * @return
     */
    @OperateSer(operationType = "add", operationName = "添加帖子标签")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxUtils labelAdd(@RequestBody Label label) {
        return sysLabelService.add(label);
    }


}
