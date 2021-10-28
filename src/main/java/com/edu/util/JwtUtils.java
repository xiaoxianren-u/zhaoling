package com.edu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author yz
 * @data: 2021/10/15 21:35 星期五
 * @file : JwtUtils.java
 */
public class JwtUtils {

    //设置有效时间
    private static final long EXPIRE = 24 * 60 * 60 * 1000;
    //秘钥 （签名）
    private static final String SECRET = "yang";
    //秘钥前缀  (加强破解难度)
    private static final String SECRET_PREFFIX = "token";
    //颁布者
    private static final String SUBJECT = "xiaomatuandui";


    /**
     * 生成token
     *
     * @return
     */
    @NotNull
    public static String generateToken(String username, int sta, Integer time) {

        int times = time;
        //设置颁布者
        String token = Jwts.builder().setSubject(SUBJECT)
                //设置token 有效时间的开始
                .setIssuedAt(new Date())
                //结束时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE * times))
                .claim("username", username)
                .claim("state", sta)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                //字符串紧凑
                .compact();

        token = SECRET_PREFFIX + token;

        return token;
    }


//    解析token

    public static Claims checkToken(String token) {

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(SECRET_PREFFIX, ""))
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return claims;

    }

}
