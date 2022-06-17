package com.sparta7.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private Key key;

    public String createToken(String email) {

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("KEY", "HelloWorld");
        payloads.put("email",email);

        Date date = new Date();
        date.setTime(date.getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        key = Keys.hmacShaKeyFor("qmffhrmdptjrkwudhawkfahffk111111111111111111111111111111111".getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("Test") // 토큰 용도
                .setExpiration(date) // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact(); // 토큰 생성
    }
}
