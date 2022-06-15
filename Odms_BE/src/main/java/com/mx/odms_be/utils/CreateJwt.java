package com.mx.odms_be.utils;

import com.mx.odms_be.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateJwt {
    public static String getoken(User user) {
        //Jwts.builder()生成
        //Jwts.parser()验证
        JwtBuilder jwtBuilder =  Jwts.builder()
                .setId(user.getId()+"")
                .setSubject(user.getUsername())    //用户名
                .setIssuedAt(new Date())//登录时间
                .signWith(SignatureAlgorithm.HS256, "my-fmx").setExpiration(new Date(new
                        Date().getTime()+86400000));
        //设置过期时间
        //前三个为载荷playload 最后一个为头部 header
//        System.out.println(jwtBuilder.compact());
        return  jwtBuilder.compact();
    }

    public static Claims tokenToOut(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("my-fmx")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("用户id:"+claims.getId());
        System.out.println("用户名:"+claims.getSubject());
        System.out.println("用户时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getIssuedAt()));System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getExpiration()));
        System.out.println("用户角色:"+claims.get("role"));
        return claims;
    }
}
