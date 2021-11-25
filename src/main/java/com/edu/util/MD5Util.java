package com.edu.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * @author yz
 * @data: 2021/9/21 20:16 星期二
 * @file : MD5Util.java
 */

/**
 * 密码加密
 */

@Component
public class MD5Util {

    //    第一次加密在前端
    private static final String SALT = "@a2b3!4d";

    @NotNull
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    @NotNull
    public static String inputPassToFromPass(String inputPass) {
        String str = SALT.charAt(0) + SALT.charAt(6) + SALT.charAt(2) + inputPass + SALT.charAt(4) + SALT.charAt(1);
        return md5(str);
    }


}
