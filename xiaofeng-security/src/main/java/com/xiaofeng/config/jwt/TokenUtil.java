package com.xiaofeng.config.jwt;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.blogs.user.entity.UserEntity;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/23 17:07
 * @Description:
 */
@Component
public class TokenUtil {
    // 版本
    public static String TOKEN_VERSION = "1";
    // 设置发行人
    public static String ISSUER = "zhicall";
    // 设置抽象主题
    public static String SUBJECT = "subject";

    //token 过期时间
    public static final Integer EXPIRATION = 10;

    // HS256 私钥
    public static String HS256KEY = "xiaofeng";

    public static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static Key signingKey = new SecretKeySpec(Base64.decodeBase64(HS256KEY), signatureAlgorithm.getJcaName());


    // 生成token
    public static String getJWTString(TokenEntity tokenEntity, Map<String, Object> claims) {

        long nowMillis = System.currentTimeMillis();
        claims.put(Claims.ID, TOKEN_VERSION);
        claims.put(Claims.ISSUER, tokenEntity);
//        claims.put(Claims.SUBJECT, SUBJECT);
//        claims.put(Claims.AUDIENCE, userEntity);
        claims.put(Claims.EXPIRATION, new Date(nowMillis + (60 * 1000 * EXPIRATION)));
        claims.put(Claims.ISSUED_AT, new Date(nowMillis));

        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        //System.out.println(System.currentTimeMillis() - nowMillis);
        jwtBuilder.signWith(signatureAlgorithm, signingKey);
        return jwtBuilder.compact();
    }

    // 有效返回true
    public static boolean isValid(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim());
            Long exp = (Long) jwsClaims.getBody().get(Claims.EXPIRATION);
            //System.out.println(exp - System.currentTimeMillis());
            return exp - System.currentTimeMillis() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Object> parseJWTtoMap(String token) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim()).getBody();
        return claims;
    }

    public static TokenEntity parseJWTtoUserEntity(String token) {
        Map<String, Object> tokenMap = parseJWTtoMap(token);
        TokenEntity tokenEntity = JSON.parseObject(JSON.toJSONString(tokenMap.get(Claims.ISSUER)), TokenEntity.class);
        return tokenEntity;
    }

//    public static String getHS512Key() {
//        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
//        String keyStr = Base64.encodeBase64String(key.getEncoded());
//        return keyStr;
//    }

    public static void main(String[] args) {
        TokenEntity tokenEntity = new TokenEntity().setUserId(123);
        String token = getJWTString(tokenEntity, new HashMap<>());
        token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOnsiaWQiOjEsInVzZXJuYW1lIjoiMTIzIiwicGFzc3dvcmQiOiIxMjMiLCJwaG9uZSI6bnVsbCwiaXNFbmFibGUiOm51bGwsImNyZWF0ZVRpbWUiOm51bGx9LCJleHAiOjE1NDA0NDIwMzU3NTQsImlhdCI6MTU0MDQ0MTQzNTc1NCwianRpIjoiMSJ9.IE-AlfRbeKV9AJuLwQkfyeJmxtJAus4wKeHlKB_yzEo";
        Map<String, Object> stringObjectMap = parseJWTtoMap(token);
        System.out.println(System.currentTimeMillis());
        System.out.println(isValid(token));
//        System.out.println(getHS512Key());
        System.out.println(stringObjectMap);
//        System.out.println(JSON.parseObject(JSON.toJSONString(stringObjectMap.get(Claims.ISSUER)), UserEntity.class));
//        System.out.println(token);
    }
}
