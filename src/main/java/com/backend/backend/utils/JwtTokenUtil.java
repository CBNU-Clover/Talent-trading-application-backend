package com.backend.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {


    public static String createToken(String nickName,String key , long expireTimeMs)
    {
        Claims claims= Jwts.claims(); // 일종의 Map
        claims.put("nickName",nickName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTimeMs))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact()
                ;
    }
}
