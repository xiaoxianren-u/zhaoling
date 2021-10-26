package com.edu.controller.back;

import com.edu.config.UserConfig;
import com.edu.intercept.OperateSer;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.NotiFy;
import com.edu.service.SysNotifyService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/24 15:23 星期日
 * @file : SysNotifyController.java
 */

@RestController
@RequestMapping(value = "/notify")
public class SysNotifyController extends AjaxUtils {


    @Autowired
    private SysNotifyService sysNotifyService;

    /**
     * 通知列表
     *
     * @param notiStatus
     * @param page
     * @param limit
     * @return
     */
    @OperateSer(operationName = "select", operationType = "查询公告通知")
    @UserLoginToken(state = 1)
    @RequestMapping("/listNot")
    public AjaxUtils listNot(@RequestParam("noti_status") Integer notiStatus,
                             @RequestParam("page") Integer page,
                             @RequestParam("limit") Integer limit) {
        page = page > 1 ? limit * (page - 1) : 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<NotiFy> list = sysNotifyService.list(notiStatus, page, limit);
        for (NotiFy s : list) {
            s.setDate(format.format(s.getNoti_time()));
        }
        int n = sysNotifyService.count(notiStatus);
        return new AjaxUtils(0, "", n, list);
    }


    /**
     * 删除通知
     *
     * @param noti_id
     * @return
     */
    @OperateSer(operationName = "delete", operationType = "删除公告通知")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public AjaxUtils delete(@RequestParam("noti_id") Integer noti_id) {
        try {
            sysNotifyService.del(noti_id);
            return new AjaxUtils(PageCodeEnum.LOGIN_SUCCESS.getBool());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg());
        }
    }


    /**
     * 新增公告通知
     *
     * @param notiFy
     * @param request
     * @return
     */
    @OperateSer(operationName = "add", operationType = "新增公告通知")
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/insertTex", method = RequestMethod.POST)
    public AjaxUtils insertStatus(@NotNull @RequestBody NotiFy notiFy, HttpServletRequest request) {
        notiFy.setNoti_time(new Date());
        notiFy.setUser_name(UserConfig.tokenUserName(request));
        try {
            sysNotifyService.insertT(notiFy);
            return new AjaxUtils(PageCodeEnum.ADD_SUCCESS.getBool(), PageCodeEnum.MODIFY_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxUtils(PageCodeEnum.ADD_FAIL.getBool(), PageCodeEnum.ADD_FAIL.getMsg());
        }
    }
}
