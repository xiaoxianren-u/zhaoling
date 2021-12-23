package com.edu.service.impl;

import com.edu.dao.SticsDao;
import com.edu.intercept.LoginInterceptor;
import com.edu.pojo.Count;
import com.edu.service.TimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author yz
 * @data: 2021/11/25 22:30 星期四
 * @file : TimedServiceImpl.java
 */

/**
 * 定时任务主要是处理后台
 * 首页的数据分析
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
        System.out.println("2222222222222222222222222222222222222");
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
    @Scheduled(cron = "0 0 11 * * ?")
    @Override
    public void reDefault() {
        System.out.println("1111111111111111111111111");
        try {
            sticsDao.reDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据分析表标签
     */
    @Override
    public void add(String label) {
        try {
            sticsDao.addLo(label);
            sticsDao.addPo(label);
            sticsDao.addRe(label);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据分析表标签
     */

    @Override
    public void dele(String label) {

        try {
            sticsDao.delLo(label);
            sticsDao.delPo(label);
            sticsDao.delRe(label);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据分析表标签
     */

    @Override
    public void update(String label, String name) {
        try {
            sticsDao.upLo(label, name);
            sticsDao.upPo(label, name);
            sticsDao.upre(label, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户数量
     * 从早上6点到晚上23点之间每30分钟执行一次
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
    @Override
    public void selUserCount() {
        try {
            //用户数量
            int n = sticsDao.selUserCount();
            //当月注册用户量
            int m = sticsDao.selUserCountNe();
            //用户量的统计
            sticsDao.insertMonth(m, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("11111111111111111111111111111111111111111111");
    }

    /**
     * 上月物品量
     * 从早上6点到晚上23点之间每30分钟执行一次
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
    @Override
    public void selPostCount() {
        try {
            //上月归还量
            int n = sticsDao.selpostCount();
            //上月总的帖子量
            int m = sticsDao.selPostCountNe();
            //帖子量的统计
            sticsDao.updatePostMonth(m, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------");
    }

    /**
     * 上周物品丢失情况
     */

    @Scheduled(cron = "0 0 6 * * ?")
//    @Scheduled(cron = "0 0/4 6-23 * * ?")
    @Override
    public void selXinqi() {

        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);

        try {
            List<Count> list = sticsDao.selXinqi();

            if (list != null) {
                for (Count c : list) {
                    sticsDao.insXinqi(c.getCount(), c.getLab_name(), currSun);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("+++++++++++++");
    }

    /**
     * 反馈量
     */
    @Scheduled(cron = "0 0/20 6-23 * * ?")
    @Override
    public void selFanKui() {
        try {
            //本周反馈量
            int n = sticsDao.getFeedCount();
            //全部反馈量
            int m = sticsDao.getFeeCount();
            //数据写入
            sticsDao.insFeed(n, m);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("+++++++++++++++++++++++++++++++++___________");
    }
}
