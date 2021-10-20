package com.edu.aop;

import com.edu.config.IpConfig;
import com.edu.config.SysContentConfig;
import com.edu.pojo.LogBook;
import com.edu.service.SysLogBookService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author yz
 * @data: 2021/9/18 13:49 星期六
 * @file : LogAspect.java
 * <p>
 * 登录日志的切面
 */

@Aspect
@Controller
public class LogAspect {

    @Autowired
    private SysLogBookService sysLogBookService;


    private long startTime;

    /**
     * 切入点  只获取登录方法的请求
     */
    @Pointcut("execution(* com.edu.controller.front.LoginController.loginPost(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        //获取当前系统时间
        startTime = System.currentTimeMillis();

    }


    @Around("pointCut()")
    public Object recordSysLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //业务方法执行
        Object result = joinPoint.proceed();
        try {
            handle(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void handle(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {


        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = clazz.getMethod(signature.getName(), signature.getParameterTypes());
        //获取存储在SysContentConfig得request
        HttpServletRequest request = SysContentConfig.getRequest();
        String ip = IpConfig.getIpAddress(request);
        String url = null;
        //类的链接
        RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
        //方法的链接
        PostMapping methodAnnotation = method.getAnnotation(PostMapping.class);
        if (methodAnnotation == null) {
            return;
        }
        if (clazzAnnotation == null && methodAnnotation == null) {
            return;
        }
        if (clazzAnnotation != null) {
            if (methodAnnotation != null) {
                url = methodAnnotation.value()[0];
            }
        } else {
            if (methodAnnotation != null) {
                url = methodAnnotation.value()[0];
            }
        }
        //获取浏览器信息
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        //获取浏览器版本号
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        //获取系统
        String system = request.getHeader("sec-ch-ua-platform");
        //获取类名及方法名
        String methodName = clazz.getName() + "." + method.getName() + "()";
        //浏览器
        String info = system + " " + browser.getName();
        //内容封装到对象中
        LogBook logBook = new LogBook(null,
                //开始执行时间
                new Date(startTime),
                // 系统与浏览器
                info,
                //类与方法
                methodName,
                //请求类型
                request.getMethod(),
                //请求链接
                request.getHeader("Host") + url,
                //IP地址
                ip,
                //用户名
                (String) request.getSession().getAttribute("name"),
                //执行时间
                (double) (System.currentTimeMillis() - startTime) / 1000,
                //是否登陆成功
                (String) request.getSession().getAttribute("log"),
                // ....
                0, null);
//        System.out.println("logBook = " + logBook);

        //清除session里的内容
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("log");
        sysLogBookService.logInsert(logBook);
    }
}