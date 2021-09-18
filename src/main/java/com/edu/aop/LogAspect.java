package com.edu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author yz
 * @data: 2021/9/18 13:49 星期六
 * @file : LogAspect.java
 */

@Aspect
@Controller
public class LogAspect {

    public LogAspect() {
        System.out.println("===========================");
    }

    @Before("execution(* com.edu.controller.IndexController.indexAction())")
    public void myBefore(JoinPoint joinPoint){
        System.out.println("前");
    }


    @After("execution(* com.edu.controller.IndexController.indexAction())")
    public void myAfter(){
        System.out.println("后");
    }


}
