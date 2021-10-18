package com.edu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author yz
 * @data: 2021/9/18 13:49 星期六
 * @file : LogAspect.java
 */

@Aspect
@Controller

public class LogAspect {
    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法


    public LogAspect() {
        System.out.println("==========================f=");
    }

    @Before("execution(* com.edu.controller.*.*.*(..))")
    public void myBefore(JoinPoint jp) {
//        visitTime = new Date();//当前时间就是开始访问的时间
//        clazz = jp.getTarget().getClass(); //具体要访问的类
//        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
//        Object[] args = jp.getArgs();//获取访问的方法的参数
//
//        //获取具体执行的方法的Method对象
//        if (args == null || args.length == 0) {
//            try {
//                method = clazz.getMethod(methodName); //只能获取无参数的方法
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Class[] classArgs = new Class[args.length];
//            for (int i = 0; i < args.length; i++) {
//                classArgs[i] = args[i].getClass();
//            }
//            try {
//                clazz.getMethod(methodName, classArgs);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }

//            System.out.println("methodName = " + methodName+"()");
        System.out.println("前");
//        }
    }

    @After("execution(* com.edu.controller.*.*.*(..))")
    public void myAfter() {
//        long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长

        String url = "";
        //获取url


        System.out.println("nihao");
        System.out.println("后");
    }
}