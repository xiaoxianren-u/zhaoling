package com.edu.service.impl;

import com.edu.dao.SticsDao;
import com.edu.intercept.LoginInterceptor;
import com.edu.pojo.Count;
import com.edu.service.TimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author yz
 * @data: 2021/11/25 22:30 星期四
 * @file : TimedServiceImpl.java
 */
@Component
public class TimedServiceImpl implements TimedService {


    public int i = 0;
    @Autowired
    private SticsDao sticsDao;

    /**
     * 访问数量增加
     */
    @Scheduled(cron = "0 */5 * * * ?")
    @Override
    public void timeAdd() {
        int count = LoginInterceptor.count;
        try {
            sticsDao.accessAdd(count);
            LoginInterceptor.count = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 每周星期天 23：59:59
     */
    @Scheduled(cron = "59 59 23 ? * SAT")
    @Override
    public void timeUp() {

        int count = LoginInterceptor.count;
        try {
            sticsDao.accessAdd(count);
            LoginInterceptor.count = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sticsDao.accessUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 饼图 数据修改
     */

    @Scheduled(cron = "0 0 */1 * * ?")
    @Override
    public void upLose() {
        List<Count> list = sticsDao.selCount();
        try {
            for (Count s : list) {
                sticsDao.upLose(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 柱状图修改
     * 从早上6点到晚上23点之间每30分钟执行一次
     */
    @Scheduled(cron = "0 0/30 6-23 * * ?")
    @Override
    public void upReturn() {

        /*
          修改丢失数据
         */
        try {
            for (Count s : sticsDao.selZhu()) {
                sticsDao.upReLose(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
          修改找回数据
         */
        try {
            for (Count s : sticsDao.selRe()) {
                sticsDao.upRe(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 柱状图恢复默认
     * 每天早上6点触发
     */
    @Scheduled(cron = "0 0 6 * * ?")
    @Override
    public void reDefault() {
        try {
            sticsDao.reDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
