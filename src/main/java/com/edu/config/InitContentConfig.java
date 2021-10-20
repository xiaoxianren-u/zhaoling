package com.edu.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yz
 * @data: 2021/10/19 19:18 星期二
 * @file : InitContentConfig.java
 */

/**
 * 将request、response对象注入
 *
 * @author yangzhan
 */
@Configuration
public class InitContentConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        SysContentConfig.setRequest((HttpServletRequest) servletRequest);
        SysContentConfig.setResponse((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
