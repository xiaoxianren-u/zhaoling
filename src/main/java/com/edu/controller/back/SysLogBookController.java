package com.edu.controller.back;

import com.edu.intercept.UserLoginToken;
import com.edu.pojo.LogBook;
import com.edu.service.SysLogBookService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/20 16:25 星期三
 * @file : SysLogBookController.java
 */


@RestController
@RequestMapping("/log")
public class SysLogBookController extends AjaxUtils {


    @Autowired
    private SysLogBookService sysLogBookService;


    /**
     * 获取日志的相应文件
     *
     * @param t 0 登录日志 ，1 操作日志
     * @return
     */

    @UserLoginToken(state = 1)
//    @OperateSer(operationName = "select操作", operationType = "通过相应的t来获取不同的日志")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxUtils logLogin(@RequestParam("t") Integer t,
                              @RequestParam("page") Integer page,
                              @RequestParam("limit") Integer limit,
                              @RequestParam("log_user") String log_user,
                              @RequestParam("log_time") String log_time,
                              @RequestParam("log_start") String log_start) {
        Date time = null;
        if (!"".equals(log_time)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                time = format.parse(log_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!"".equals(log_start)) {
            log_start = new String(log_start.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        System.out.println("log_start = " + log_start);
        page = page > 1 ? limit * (page - 1) : 0;
        List<LogBook> list = sysLogBookService.selectList(t, page, limit, log_user, time, log_start);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (LogBook s : list) {
            s.setDate(formatter.format(s.getLog_date()));
        }
        int n = sysLogBookService.selectCount(t, log_user, time, log_start);
        return new AjaxUtils(n, list);
    }
}
