package com.edu.controller.back;

import com.alibaba.fastjson.JSON;
import com.edu.intercept.UserLoginToken;
import com.edu.pojo.NotiFy;
import com.edu.service.SysNotifyService;
import com.edu.util.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/24 15:23 星期日
 * @file : SysNotifyController.java
 */

@RestController
@RequestMapping(value = "/notify")
public class SysNotifyController {


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

    @ResponseBody
    @UserLoginToken(state = 1)
    @RequestMapping("/listNot")
    public String listNot(@RequestParam("noti_status") Integer notiStatus,
                          @RequestParam("page") Integer page,
                          @RequestParam("limit") Integer limit) {
        page = page > 1 ? limit * (page - 1) : 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<NotiFy> list = sysNotifyService.list(notiStatus, page, limit);
        for (NotiFy s : list) {
            s.setDate(format.format(s.getNoti_time()));
        }
        int n = sysNotifyService.count(notiStatus);
        return "{\"code\":0,\"msg\":\"\",\"count\":" + n + ",\"data\":" + JSON.toJSONString(list) + "}";
    }


    @ResponseBody
    @UserLoginToken(state = 1)
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String delete(@RequestParam("noti_id") Integer noti_id) {
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            sysNotifyService.del(noti_id);
            hashMap.put("bool", true);
        } catch (Exception e) {
            hashMap.put("bool", PageCodeEnum.REMOVE_FAIL.getBool());
            hashMap.put("msg", PageCodeEnum.REMOVE_FAIL.getMsg());
            e.printStackTrace();
        }
        return JSON.toJSONString(hashMap);

    }


}
