package com.edu.intercept;

import java.lang.annotation.*;

/**
 * @author yz
 * @data: 2021/10/20 18:01 星期三
 * @file : OperateSer.java
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateSer {

        /**
         * 要执行的操作类型比如：add操作
         **/
        String operationType() default "";

        /**
         * 要执行的具体操作比如：添加用户
         **/
        String operationName() default "";
}
