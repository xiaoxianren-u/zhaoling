package com.edu.config;

import com.edu.util.JwtUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author yz
 * @data: 2021/10/21 21:28 星期四
 * @file : UserConfig.java
 */
@Component
public class UserConfig {

    public static String cookie = null;
    public static String token = null;

    public static String tokenUserName(HttpServletRequest request) {

        cookie = request.getHeader("Cookie");
        String[] output = cookie.split(";");
        for (int i = 0; i <= cookie.length(); i++) {
            if (output[i].contains("token")) {
                token = output[i];
                break;
            }
        }
        return (String) Objects.requireNonNull(JwtUtils.checkToken(token)).get("username");
    }

}
