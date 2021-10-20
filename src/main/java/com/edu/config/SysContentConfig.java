package com.edu.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yz
 * @data: 2021/10/19 19:17 星期二
 * @file : SysContentConfig.java
 */

/**
 * 定义request、response对象的类
 *
 * @author yangzhan
 */
@Configuration
public class SysContentConfig {

    private static final ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    private static final ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletResponse getResponse() {
        return responseLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static HttpSession getSession() {
        return requestLocal.get().getSession();
    }


}
