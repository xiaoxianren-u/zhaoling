package com.edu.intercept;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yz
 * @data: 2021/10/15 22:37 星期五
 * @file : UserLoginAnno.java
 */
//用于判断该控制器是否需要用户登录权限  ,需要有token
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    //用于校验是否要拦截
    boolean value() default true;

    //用于校验该角色是否有权限
    int state() default 0;
}
