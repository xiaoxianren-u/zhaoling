package com.edu.util;

import org.apache.commons.codec.digest.DigestUtils;
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
    private static final String SALT = "1a2b3c4d";

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToFromPass(String inputPass) {
        String str = SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(4) + SALT.charAt(1);
        return md5(str);
    }

    //    第二次加密在后端 salt 随机生成的眼并且要保存到后端
    public static String fromPassToInputPass(String fromPass, String salt) {
        String str = salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(4) + salt.charAt(1);
        return md5(str);
    }

    public static String inputPassToFrom(String inputPass, String salt) {

        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToInputPass(fromPass, salt);
        return dbPass;

    }

    public static void main(String[] args) {
//        27b523c64b389ec00aef46d9468211f1
        System.out.println("inputPassToFromPass() = " + inputPassToFromPass("12345678"));
        System.out.println("inputPassToFrom(\"27b523c64b389ec00aef46d9468211f1\",SALT) = " + inputPassToFrom("27b523c64b389ec00aef46d9468211f1", SALT));

    }


}
