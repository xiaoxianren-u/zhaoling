package com.edu.service;

import com.edu.util.AjaxUtils;

/**
 * @author yz
 * @data: 2021/12/15 20:51 星期三
 * @file : GrateFulService.java
 */


public interface GrateFulService {

    /**
     * 感谢信表
     *
     * @param page
     * @param limit
     * @param username
     * @param date
     * @return
     */
    AjaxUtils list(Integer page, Integer limit, String username, String date);


    /**
     * 删除感谢信
     *
     * @param id
     * @return
     */
    AjaxUtils del(Integer id);
}
