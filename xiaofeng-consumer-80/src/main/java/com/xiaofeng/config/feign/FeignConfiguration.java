package com.xiaofeng.config.feign;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/19 15:40
 * @Description: 与feign结合 给每次请求服务端时都携带验证信息和session
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        getHeader(template);
    }

    public static void getHeader(RequestTemplate template){
        //feign session丢失问题
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);
                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    template.header(name, value);
                }
            }
        }

        //服务端认证信息
//        HttpHeaders headers=new HttpHeaders();
        String auth="admin:lzj1158119024";//认证的原始信息
        byte[] encodeAuth= Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));//将原始认证信息进行Base64加密
        String authHeader="Basic "+new String(encodeAuth);//加密后的认证信息要与Basic有个空格
//        headers.set("Authorization", authHeader);
        template.header("Authorization", authHeader);
    }

//    @Bean
    /*public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
//            String sessionId = sessionConsumerController.getSession();
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            System.out.println("sessionId--->"+sessionId);
            if (!Strings.isNullOrEmpty(sessionId)) {
                requestTemplate.header("Cookie", "SESSION=" + sessionId);
            }
        };
    }*/



}
