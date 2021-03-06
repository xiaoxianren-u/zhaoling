package com.edu.intercept;

import com.edu.service.SysUserService;
import com.edu.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yz
 * @data: 2021/10/15 22:15 星期五
 * @file : LoginInterceptor.java
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static int count = 0;
    @Autowired
    private SysUserService sysUserService;


    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        /* 记录网站访问查次数*/

        String token = null;
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.value()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            //对象验证 ，0位普通用户访问 1为管理员访问
            int state = userLoginToken.state();
            if (userLoginToken.value()) {
                String cookie = request.getHeader("Cookie");
                String[] output = null;
                try {
                    output = cookie.split("[;,=]");
                } catch (Exception e) {
                    e.printStackTrace();
//                    throw  new ModelAndView("redirect:/toList");
                    response.sendRedirect("/404");
//                    request.getRequestDispatcher("/login").forward(request,response);
                    return false;
//                    throw new RuntimeException("无令牌，请重新登录");


                }
                try {
                    for (int i = 0; i < cookie.length(); i++) {
                        if (output[i].contains("token")) {
                            token = output[i];
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/401");

                    return false;
//                    throw new RuntimeException("无令牌，请重新登录");
                }
                if (token == null) {
                    token = request.getParameter("token");
                }

                // 执行认证
                if (token == null) {
                    response.sendRedirect("/404");
                    return false;
//                    throw new RuntimeException("无令牌，请重新登录 ");
                }
                // 获取 token 中的 username
                Claims claims = JwtUtils.checkToken(token);
                if (claims == null) {
                    response.sendRedirect("/404");
                    throw new RuntimeException("身份错误！！！");
                } else {
                    String username = (String) claims.get("username");
                    int n = sysUserService.selectRec(username);
                    if (n != 1) {
                        throw new RuntimeException("用户不存在，请重新登录！！！");
                    }
                }
                if (state != (int) claims.get("state")) {
                    response.sendRedirect("/403");
//                    throw new RuntimeException("该用户无权限访问");

                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {

    }
}
