package com.edu.service.impl;

import com.edu.dao.SysUserDao;
import com.edu.pojo.User;
import com.edu.service.SysUserService;
import com.edu.util.AjaxUtils;
import com.edu.util.PageCodeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yz
 * @data: 2021/10/2 15:28 星期六
 * @file : SysUserServiceImpl.java
 */

@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 管理员列表
     *
     * @return
     */
    @Override
    public List<User> selectAdminList(Integer page, Integer limit, String username) {
        return sysUserDao.selectAdminList(page, limit, username);
    }

    /**
     * 管理员数量
     *
     * @param username
     * @return
     */
    @Override
    public int adminCount(String username) {
        return sysUserDao.adminCount(username);
    }

    /**
     * 查询是否有该用户
     *
     * @param username
     * @return
     */
    @Override
    public int selectRec(String username) {
        return sysUserDao.selectRec(username);
    }

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param date
     * @return
     */
    @Override
    public int insertRegister(String username, String password, Date date, String home) {
        return sysUserDao.insertRegister(username, password, date, home);
    }

    /**
     * 用户或管理员登录
     *
     * @param username
     * @param password
     * @param
     * @return
     */
    @Override
    public int countUserAdmin(String username, String password) {
        return sysUserDao.countUserAdmin(username, password);
    }

    /**
     * 最后一次登录
     *
     * @param username
     * @param date
     * @return
     */
    @Override
    public int updateLogin(String username, Date date) {
        return sysUserDao.updateLogin(username, date);
    }

    /**
     * 查询角色类型是普通用户还是管理员
     *
     * @param username
     * @return
     */
    @Override
    public String selectStatus(String username) {
        return sysUserDao.selectStatus(username);
    }

    /**
     * 用户列表
     *
     * @return
     */
    @Override
    public List<User> selectUserList(Integer page, Integer limit, String username) {
        return sysUserDao.selectUserList(page, limit, username);
    }

    /**
     * 普通用户数量
     *
     * @return
     */
    @Override
    public int selectCount(String username) {
        return sysUserDao.selectCount(username);
    }

    /**
     * 用户黑名单列表
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<User> selectBlackList(Integer page, Integer limit, String username) {
        return sysUserDao.selectBlackList(page, limit, username);
    }

    /**
     * 黑名单数量
     *
     * @return
     */
    @Override
    public int selectblackCount(String username) {
        return sysUserDao.selectblackCount(username);
    }

    /**
     * 获取某位用户的信息
     *
     * @param username
     * @return
     */
    @Override
    public User selectBasic(String username) {
        return sysUserDao.selectBasic(username);
    }

    /**
     * 基本信息修改
     *
     * @param user
     */

    @Override
    public void updateBasic(User user) {
        sysUserDao.updateBasic(user);
    }


    /**
     * 获取密码
     *
     * @param user_name
     * @return
     */
    @Override
    public String selectPassword(String user_name) {
        return sysUserDao.selectPassword(user_name);
    }

    /**
     * 更改密码
     *
     * @param name
     */
    @Override
    public void updatePass(String username, String name) {
        sysUserDao.updatePass(username, name);
    }

    /**
     * 修改用户权限或拉黑
     *
     * @param user
     * @return
     */
    @Override
    public AjaxUtils upUser(User user) {

        if (sysUserDao.selectPassword("admin").equals(user.getPass_word())) {
            if (user.getPull_black() == 1) {
                try {
                    sysUserDao.updataPull(user);
                    return new AjaxUtils(true, "该用户已拉黑");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "该用户拉黑失败,出现错误！！");
                }
            } else {
                try {
                    user.setUser_email("管理员");
                    sysUserDao.updataStatus(user);
                    return new AjaxUtils(true, "该用户已设为管理员");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "设为管理员失败,出现错误！！");
                }
            }

        }
        return new AjaxUtils(false, "口令不匹配");
    }


    /**
     * 对现有的管理员处理
     *
     * @param user
     * @return
     */
    @Override
    public AjaxUtils upUserNot(@NotNull User user) {

        if (sysUserDao.selectPassword("admin").equals(user.getPass_word())) {
            if (user.getPull_black() == 1) {
                try {
                    sysUserDao.updataPull(user);
                    return new AjaxUtils(true, "该用户已拉黑");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "该用户拉黑失败,出现错误！！");
                }
            } else {
                try {
                    user.setUser_email("普通用户");
                    sysUserDao.updataStatus(user);
                    return new AjaxUtils(true, "该用户已设为普通用户");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "设为管理员失败,出现错误！！");
                }
            }

        }
        return new AjaxUtils(false, "口令不匹配");
    }

    /**
     * 修改黑名单权限或拉黑
     *
     * @param user
     * @return
     */

    @Override
    public AjaxUtils upUserBlack(@NotNull User user) {

        if (sysUserDao.selectPassword("admin").equals(user.getPass_word())) {
            if (user.getPull_black() == 1) {
                try {
                    user.setPull_black(0);
                    sysUserDao.updataPull(user);
                    return new AjaxUtils(true, "该用户已恢复");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "该用户恢复失败,出现错误！！");
                }
            } else if (user.getPull_black() == 2) {
                try {
                    user.setUser_email("管理员");
                    sysUserDao.updataStatus(user);
                    return new AjaxUtils(true, "该用户已设为管理员");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "设为管理员失败,出现错误！！");
                }
            } else {
                try {
                    user.setUser_email("普通用户");
                    sysUserDao.updataStatus(user);
                    return new AjaxUtils(true, "该用户已设为普通用户");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxUtils(true, "设为管理员失败,出现错误！！");
                }
            }

        }
        return new AjaxUtils(false, "口令不匹配");
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @Override
    public AjaxUtils dlUser(User user) {
        if (sysUserDao.selectPassword("admin").equals(user.getPass_word())) {

            try {
                sysUserDao.dlUser(user);
                return new AjaxUtils(PageCodeEnum.REMOVE_SUCCESS.getBool(), PageCodeEnum.REMOVE_SUCCESS.getMsg());
            } catch (Exception e) {
                e.printStackTrace();
                return new AjaxUtils(PageCodeEnum.REMOVE_FAIL.getBool(), PageCodeEnum.REMOVE_FAIL.getMsg());
            }
        }
        return new AjaxUtils(false, "口令不匹配");
    }
}
