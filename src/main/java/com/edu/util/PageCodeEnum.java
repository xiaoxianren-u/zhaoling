package com.edu.util;

/**
 * @author yz
 * @data: 2021/9/24 22:34 星期五
 * @file : PageCodeEnum.java
 */


/**
 * 返回前端的枚举
 *
 * @author yangzhan
 */
public enum PageCodeEnum {

    ADD_SUCCESS(1000, "新增成功！", true),
    ADD_FAIL(1001, "新增失败！", false),
    MODIFY_SUCCESS(1100, "修改成功！", true),
    MODIFY_FAIL(1101, "修改失败！", false),
    REMOVE_SUCCESS(1200, "删除成功！", true),
    REMOVE_FAIL(1201, "删除失败！", false),
    LOGIN_FAIL(1301, "登录失败！用户名密码错误！", false),
    LOGIN_SUCCESS(1301, "登录成功！", true),
    SESSION_TIMEOUT(1302, "session超时，请重新登录！", false),
    NO_AUTH(1303, "没有权限访问请求资源，请切换账户后重试！", false),
    USERNAME_EXISTS(1401, "用户名已存在！", false),
    SUB_MENU_EXISTS(1403, "菜单下还存在子菜单！", false),
    SUB_MENU_EXISTS_M(1404, "上级目录（菜单）与类型不符！", false),
    ;


    private final Integer code;
    private final String msg;
    private final Boolean bool;


    /**
     * 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
     *
     * @param code
     * @param msg
     */
    PageCodeEnum(Integer code, String msg, Boolean bool) {
        this.code = code;
        this.msg = msg;
        this.bool = bool;
    }

//    sdf

    /**
     * PageCodeEnum
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getBool() {
        return bool;
    }
}
