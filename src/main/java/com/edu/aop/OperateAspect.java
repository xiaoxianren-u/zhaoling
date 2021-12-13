package com.edu.aop;

import com.edu.config.IpConfig;
import com.edu.config.SysContentConfig;
import com.edu.config.UserConfig;
import com.edu.intercept.OperateSer;
import com.edu.pojo.LogBook;
import com.edu.service.SysLogBookService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author yz
 * @data: 2021/10/20 17:45 星期三
 * @file : OperateAspect.java
 * <p>
 * 操作日志的切面
 */

@Aspect
@Component
public class OperateAspect {


    public static String token = null;
    public static Long startTime;
    public String username = null;
    public String cookie = null;
    @Autowired
    private SysLogBookService sysLogBookService;

    @Pointcut("execution(* com.edu.controller.back.*.*(..))")
    public void pointCut() {
    }


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    @Around("pointCut()")
    public Object recordSysLog(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = SysContentConfig.getRequest();
        // 反射获取目标类
        Class<?> clazz = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method method = clazz.getMethod(signature.getName(), signature.getParameterTypes());
        // 拿到方法定义的注解信息
        OperateSer annotation = method.getDeclaredAnnotation(OperateSer.class);

        if (annotation != null) {
            /*获取页面的用户名*/
            username = UserConfig.tokenUserName(request);
            //获取浏览器信息
            Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
            //浏览器 及系统
            String info = request.getHeader("sec-ch-ua-platform") + " " + browser.getName();
            //获取类名及方法名
            String methodName = clazz.getName() + "." + method.getName() + "()";
            String url = getUrl(clazz, method);
            String ip = IpConfig.getIpAddress(request);

            String operationName = annotation.operationName();
            String operationType = annotation.operationType();
            LogBook logBook = new LogBook(null,
                    //开始执行时间
                    new Date(startTime),
                    // 系统与浏览器
                    info,
                    //类与方法
                    methodName + "." + operationName + "， 描述：" + operationType,
                    //请求类型
                    request.getMethod(),
                    //请求链接
                    request.getHeader("Host") + url,
                    //IP地址
                    ip,
                    //用户名
                    username,
                    //执行时间
                    (double) (System.currentTimeMillis() - startTime) / 1000,
                    //是否登陆成功
                    null,
                    // ....
                    1, null);

            try {
                sysLogBookService.logInsert(logBook);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return joinPoint.proceed();
    }


    /*获取url */

    public String getUrl(@NotNull Class<?> clazz, Method method) {

        String url = null;
        //类的链接
        RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
        //方法的链接
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
//        if (methodAnnotation == null) {
//            return;
//        }
//        if (clazzAnnotation == null && methodAnnotation == null){
//            return ;
//        }
        if (clazzAnnotation != null) {
            if (methodAnnotation != null) {
                url = clazzAnnotation.value()[0] + methodAnnotation.value()[0];
            }
        } else {
            if (methodAnnotation != null) {
                url = methodAnnotation.value()[0];
            }
        }
        return url;
    }


}


