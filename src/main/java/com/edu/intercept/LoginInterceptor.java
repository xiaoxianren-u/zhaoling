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

    @Autowired
    private SysUserService sysUserService;


    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
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
            if (userLoginToken.value()) {
                String cookie = request.getHeader("Cookie");
                String[] output = cookie.split(";");
                for (int i = 0; i <= cookie.length(); i++) {
                    if (output[i].contains("token")) {
                        token = output[i];
                        break;
                    }
                }
                if (token == null) {
                    token = request.getParameter("token");
                }

                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无令牌，请重新登录");
                }
                // 获取 token 中的 username
                Claims claims = JwtUtils.checkToken(token);
                if (claims == null) {
                    throw new RuntimeException("身份错误！！！");
                } else {
                    String username = (String) claims.get("username");
                    int n = sysUserService.selectRec(username);
                    if (n != 1) {
                        throw new RuntimeException("用户不存在，请创新登录！！！");
                    }
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
