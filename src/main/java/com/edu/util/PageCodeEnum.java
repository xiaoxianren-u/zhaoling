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

    ADD_SUCCESS(1000, "新增成功！"),
    ADD_FAIL(1001, "新增失败！"),
    MODIFY_SUCCESS(1100, "修改成功！"),
    MODIFY_FAIL(1101, "修改失败！"),
    REMOVE_SUCCESS(1200, "删除成功！"),
    REMOVE_FAIL(1201, "删除失败！"),
    LOGIN_FAIL(1301, "登录失败！用户名密码错误！"),
    SESSION_TIMEOUT(1302, "session超时，请重新登录！"),
    NO_AUTH(1303, "没有权限访问请求资源，请切换账户后重试！"),
    USERNAME_EXISTS(1401, "用户名已存在！"),
    GROUPNAME_EXISTS(1402, "用户组名已存在！"),
    SUB_MENU_EXISTS(1403, "菜单下还存在子菜单！"),
    ASSIGN_SUCCESS(1500, "分配成功！"),
    ASSIGN_FAIL(1501, "分配失败！"),
    ORDER_SUCCESS(1600, "排序成功！"),
    ORDER_FAIL(1601, "排序失败！"),
    ;

    private Integer code;
    private String msg;


    /**
     * 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
     *
     * @param code
     * @param msg
     */
    private PageCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
